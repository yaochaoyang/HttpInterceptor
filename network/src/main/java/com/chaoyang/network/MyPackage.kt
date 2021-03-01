package com.chaoyang.network

import com.chaoyang.network.interceptor.MyInterceptor
import com.chaoyang.network.processor.DefaultVerifyProcessor
import com.chaoyang.network.processor.EmptyPollingProcessor
import com.chaoyang.network.processor.PollingProcessor
import com.chaoyang.network.processor.VerifyProcessor


/**
 * @author: chaoyang
 * @create: 2019-12-03 17:51
 * @description: 请自行添加对class描述
 **/
class MyPackage<T : MyRequest, R : MyResult> {
    private lateinit var requestInfo: T
    val result: R
    var interceptors: MutableList<MyInterceptor<T, R>> = ArrayList()
    private val pollingProcessor: PollingProcessor<in R> = EmptyPollingProcessor
    var timeOut: Long = -1L // milliseconds
    private var verifyProcessor: VerifyProcessor<in T, in R> = DefaultVerifyProcessor()
    internal val wrapper: MyWrapper<T, R> by lazy {
        MyWrapper(requestInfo, pollingProcessor, interceptors, timeOut, verifyProcessor)
    }

    constructor(request: T, result: R) {
        this.requestInfo = request
        this.result = result
    }

    fun addInterceptor(vararg interceptor: MyInterceptor<T, R>): MyPackage<T, R> {
        interceptors.addAll(interceptor)
        return this
    }

    fun getExecutor(): MyExecutor<T, R> {
        return MyExpress.pack(this)
    }
}