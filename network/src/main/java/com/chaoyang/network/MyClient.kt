package com.chaoyang.network

import com.chaoyang.network.interceptor.MyInterceptor
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

/**
 * @author: chaoyang
 * @create: 2019-12-03 17:40
 * @description: 请自行添加对class描述
 **/
class MyClient(val executor: ExecutorService = Executors.newCachedThreadPool()) {

    internal val interceptors: MutableList<MyInterceptor<*, *>> = mutableListOf()

    fun <T : MyRequest, R : MyResult> addInterceptor(interceptor: MyInterceptor<T, R>): MyClient {
        interceptors.add(interceptor)
        return this
    }

    fun <T : MyRequest, R : MyResult> newCall(
        wrapper: MyWrapper<T, R>,
        result: R): MyCall<T, R> {
        return MyRealCall(wrapper, result, this)
    }
}