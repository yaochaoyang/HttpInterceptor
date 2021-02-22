package com.chaoyang.network.interceptor

import com.chaoyang.network.L
import com.chaoyang.network.OpMpRequest
import com.chaoyang.network.OpMpResult

/**
 * @author: chaoyang
 * @create: 2019-12-23 16:34
 * @description: 请自行添加对class描述
 **/
class OpMpPollingInterceptor<T : OpMpRequest, R : OpMpResult> : OpMpInterceptor<T, R> {


    override fun intercept(chain: OpMpInterceptor.Chain<T, R>): R {
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