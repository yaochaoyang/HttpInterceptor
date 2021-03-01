package com.chaoyang.network.interceptor

import com.chaoyang.network.*
import com.chaoyang.network.processor.ExecuteProcessor


/**
 * @author: chaoyang
 * @create: 2019-12-05 10:23
 * @description: 请自行添加对class描述
 *
 **/
class ExecuteInterceptor<T : MyRequest, R : MyResult> : MyInterceptor<T, R> {

    override fun intercept(chain: MyInterceptor.Chain<T, R>): R {
        try {
            val wrapper = chain.wrapper
            val executeProcessor = ExecuteProcessor<T>()
            val result = executeProcessor.processorRequest(wrapper.request)
            val task = chain.result
            L.i(" ExecuteInterceptor result = $task " + result + "  当前线程名称 = " + Thread.currentThread().name)
            val opMpResult: R = fromJson(result, task::class.java)
            L.i("ExecuteInterceptor 36 result = ${opMpResult.toString()}")
            return opMpResult
        } catch (e: Exception) {
            throw MyException("505", " NetWork Exception")
        }
    }
}