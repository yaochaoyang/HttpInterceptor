package com.chaoyang.network.interceptor

import com.chaoyang.network.L
import com.chaoyang.network.MyRequest
import com.chaoyang.network.MyResult

/**
 * @author: chaoyang
 * @create: 2019-12-23 16:34
 * @description: 请自行添加对class描述
 **/
class PollingInterceptor<T : MyRequest, R : MyResult> : MyInterceptor<T, R> {


    override fun intercept(chain: MyInterceptor.Chain<T, R>): R {
        Thread.sleep(1000L)
        val wrapper = chain.wrapper
        L.i(" OpMpPollingInterceptor request = " + wrapper.request.toString())
        val response = chain.proceed(wrapper)
        L.i("  response = " + response.toString() + "  当前线程名称 = " + Thread.currentThread().name)
        var count: Int = 1
        while (count < 3) {
            Thread.sleep(1000L)
            L.i(" request = ${chain.wrapper.request}")
            L.i(" response = $response")
            count++
        }
        return chain.proceed(chain.wrapper)
    }

}