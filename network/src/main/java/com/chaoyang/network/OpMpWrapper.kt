package com.chaoyang.network

import com.chaoyang.network.interceptor.OpMpInterceptor
import com.chaoyang.network.processor.DefaultVerifyProcessor
import com.chaoyang.network.processor.EmptyPollingProcessor
import com.chaoyang.network.processor.PollingProcessor
import com.chaoyang.network.processor.VerifyProcessor


/**
 * @author: chaoyang
 * @create: 2019-12-03 16:46
 * @description: 请自行添加对class描述
 **/
class OpMpWrapper<T : OpMpRequest, R : OpMpResult>(
        val request: T,
        val pollingProcessor: PollingProcessor<in R> = EmptyPollingProcessor,
        internal val interceptors: List<OpMpInterceptor<T, R>> = emptyList(),
        val timeOut: Long = -1L,
        val verifyProcessor: VerifyProcessor<in T, in R> = DefaultVerifyProcessor()
)