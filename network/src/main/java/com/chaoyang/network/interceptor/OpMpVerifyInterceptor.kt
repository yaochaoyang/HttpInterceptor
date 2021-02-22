package com.chaoyang.network.interceptor

import android.app.Activity
import com.chaoyang.network.*
import java.util.concurrent.ConcurrentHashMap

/**
 * @author: chaoyang
 * @create: 2019-12-17 18:13
 * @description: 请自行添加对class描述
 **/
class OpMpVerifyInterceptor<T : OpMpRequest, R : OpMpResult>(
    observable: ActivityObservable,
    private val verifier: DefaultVerifier = DefaultVerifier()
) : OpMpInterceptor<T, R> {
    @Volatile
    private var topActivity: Activity? = null

    private val locks: MutableMap<Any, Object> = ConcurrentHashMap()
    private val observer = object : ActivityObserver {
        override fun onActivityResumed(activity: Activity) {
            super.onActivityResumed(activity)
            topActivity = activity
        }

        override fun onActivityDestroyed(activity: Activity) {
            super.onActivityDestroyed(activity)
            if (topActivity === activity) {
                topActivity = null
            }
        }
    }

    init {
        observable.registerActivityObserver(observer)
    }

    override fun intercept(chain: OpMpInterceptor.Chain<T, R>): R {
        val wrapper = chain.wrapper
        val request = wrapper.request
        val processor = chain.wrapper.verifyProcessor
        var result = chain.proceed(wrapper)
        var verifySuccess = false
        try {
            while (processor.needVerify(wrapper.request, result)) {
                L.i("OpMpVerifyInterceptor while 当前线程" + Thread.currentThread().name)
                val activity = checkedActivity()
                val params = processor.buildVerifyParams(wrapper.request, result)
                verifier.startVerify(activity, params, object : VerifyListener {
                    override fun onResult(result: String) {
                        //TODO something
                        notify(request)
                    }

                    override fun onAction(action: String) {
                        verifySuccess = false
                        //TODO something
                        notify(request)
                    }
                })
                L.i("OpMpVerifyInterceptor result = $result  while 当前线程" + Thread.currentThread().name)
                wait(request)
                if (!verifySuccess) {
                    throw OpMpException("500", "onAction")
                }
                L.i("OpMpVerifyInterceptor result = $result   当前线程" + Thread.currentThread().name)
                if (processor.isNeedDoubleVerify()) {
                    result = chain.proceed(wrapper)
                } else {
                    throw OpMpException("500", "进入二次循环校验")
                }
            }
        } finally {
            notify(request)
            locks.remove(request)
        }
        return result
    }

    private fun checkedActivity(): Activity {
        val activity = topActivity
        if (activity == null || activity.isFinishing) {
            throw OpMpException("500", "topActivity is null or isFinishing.")
        }
        return activity
    }

    private fun wait(request: OpMpRequest) {
        val lock = locks.getOrPut(request) { Object() }
        //[request : object]
        //wait = java.lang.Object@9737b1e
        L.i("OpMpVerifyInterceptor wait = $lock")
        synchronized(lock) {
            lock.wait()
        }
    }

    private fun notify(request: OpMpRequest) {
        val lock = locks[request]
        L.i("OpMpVerifyInterceptor notify = $lock")
        if (lock != null) {
            synchronized(lock) {
                lock.notify()
            }
        }
    }
}