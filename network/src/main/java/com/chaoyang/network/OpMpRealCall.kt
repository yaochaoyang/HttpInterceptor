package com.chaoyang.network

import com.chaoyang.network.interceptor.ExecuteInterceptor
import com.chaoyang.network.interceptor.OpMpInterceptor
import java.util.concurrent.Callable
import java.util.concurrent.Future
import java.util.concurrent.TimeUnit

/**
 * @author: chaoyang
 * @create: 2019-12-04 11:13
 * @description: 请自行添加对class描述
 **/
class OpMpRealCall<T : OpMpRequest, R : OpMpResult>(
    override val wrapper: OpMpWrapper<T, R>,
    override val result: R,
    private val client: OpMpClient
) : OpMpCall<T, R> {

    private lateinit var callBack: OpMpCallBack

    private val future: Future<R> by lazy {
        L.i("当前线程名称 = " + Thread.currentThread().name)
        client.executor.submit(Callable { execute() })
    }

    override fun cancel(): Boolean {
        return future.cancel(true)
    }

    override fun execute(): R {
        val wrappers = wrapper.interceptors
        val clients = client.interceptors as List<OpMpInterceptor<T, R>>
        val initialCapacity = wrappers.size + clients.size + 1
        val interceptors = ArrayList<OpMpInterceptor<T, R>>(initialCapacity)
        L.i("SensitiveInterceptor 当前线程名称 = " + Thread.currentThread().name + " wrappers = ${wrappers.size}  ${clients.size} ")
        interceptors.addAll(wrappers)
        interceptors.addAll(clients)
        interceptors.add(ExecuteInterceptor())
        val opMpRealChain = OpMpRealChain(interceptors, 0, result, wrapper, this)
        return opMpRealChain.proceed(wrapper)
    }

    override fun enqueue(callBack: OpMpCallBack) {
        this.callBack = callBack
        this.callBack.onStart()
        client.executor.execute(AsyncCall(future, wrapper, callBack))
    }

    private class AsyncCall<T : OpMpRequest, R : OpMpResult>(
        val future: Future<R>,
        val wrapper: OpMpWrapper<T, R>,
        val callBack: OpMpCallBack
    ) : Runnable {

        override fun run() {
            try {
                val result = if (wrapper.timeOut > 0L) {
                    future.get(wrapper.timeOut, TimeUnit.MILLISECONDS)
                } else {
                    future.get()
                }
                callBack.onSuccess(result)
            } catch (e: Throwable) {
                if (e is OpMpException) {
                    L.i(" OpMpException " + e.cause)
                } else {
                    if (e.cause is OpMpException) {
                        L.i(" ===== OpMpException ")
                    }
                    L.i(" Exception $e  ====== " + e.cause)
                }
                callBack.onFailure(OpMpException("500", "${e.message}"))
            } finally {
                callBack.onFinish()
            }

        }
    }
}