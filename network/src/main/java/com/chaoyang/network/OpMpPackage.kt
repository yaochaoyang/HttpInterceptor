package com.chaoyang.network

import com.chaoyang.network.interceptor.OpMpInterceptor
import com.chaoyang.network.processor.DefaultVerifyProcessor
import com.chaoyang.network.processor.EmptyPollingProcessor
import com.chaoyang.network.processor.PollingProcessor
import com.chaoyang.network.processor.VerifyProcessor


/**
 * @author: chaoyang
 * @create: 2019-12-03 17:51
 * @description: 请自行添加对class描述
 **/
class OpMpPackage<T : OpMpRequest, R : OpMpResult> {
    private lateinit var requestInfo: T
    val result: R
    var interceptors: MutableList<OpMpInterceptor<T, R>> = ArrayList()
    private val pollingProcessor: PollingProcessor<in R> = EmptyPollingProcessor
    var timeOut: Long = -1L // milliseconds
    private var verifyProcessor: VerifyProcessor<in T, in R> = DefaultVerifyProcessor()
    internal val wrapper: OpMpWrapper<T, R> by lazy {
        OpMpWrapper(requestInfo, pollingProcessor, interceptors, timeOut, verifyProcessor)
    }

    constructor(request: T, result: R) {
        this.requestInfo = request
        this.result = result
    }

    fun addInterceptor(vararg interceptor: OpMpInterceptor<T, R>): OpMpPackage<T, R> {
        interceptors.addAll(interceptor)
        return this
    }

    fun getExecutor(): OpMpExecutor<T, R> {
        return OpMpExpress.pack(this)
    }
}