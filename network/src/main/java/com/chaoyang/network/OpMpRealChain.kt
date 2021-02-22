package com.chaoyang.network

import com.chaoyang.network.interceptor.OpMpInterceptor


/**
 * @author: chaoyang
 * @create: 2019-12-04 17:56
 * @description: 请自行添加对class描述
 **/
internal class OpMpRealChain<T : OpMpRequest, R : OpMpResult>(
    private val interceptors: List<OpMpInterceptor<T, R>>,
    private val index: Int,
    override val result: R,
    override val wrapper: OpMpWrapper<T, R>,
    private var opMpaasCall: OpMpCall<T, R>
) : OpMpInterceptor.Chain<T, R> {

    override fun proceed(wrapper: OpMpWrapper<T, R>): R {
        val next = OpMpRealChain(interceptors, index + 1, result, wrapper, opMpaasCall)
        val interceptor = interceptors[index]
//        println(" OpMpRealChain index = $index interceptor name = " + interceptor.javaClass.canonicalName)
        L.i(" OpMpRealChain index = $index 当前线程名称 = " + Thread.currentThread().name)
        return interceptor.intercept(next)
    }


}