package com.chaoyang.network

import com.chaoyang.network.interceptor.OpMpInterceptor
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

/**
 * @author: chaoyang
 * @create: 2019-12-03 17:40
 * @description: 请自行添加对class描述
 **/
class OpMpClient(
    val client: IClient,
    val executor: ExecutorService = Executors.newCachedThreadPool()) {

    internal val interceptors: MutableList<OpMpInterceptor<*, *>> = mutableListOf()

    fun <T : OpMpRequest, R : OpMpResult> addInterceptor(interceptor: OpMpInterceptor<T, R>): OpMpClient {
        interceptors.add(interceptor)
        return this
    }

    fun <T : OpMpRequest, R : OpMpResult> newCall(
        wrapper: OpMpWrapper<T, R>,
        result: R): OpMpCall<T, R> {
        return OpMpRealCall(wrapper, result, this)
    }
}