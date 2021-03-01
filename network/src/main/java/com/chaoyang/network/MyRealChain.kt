package com.chaoyang.network

import com.chaoyang.network.interceptor.MyInterceptor


/**
 * @author: chaoyang
 * @create: 2019-12-04 17:56
 * @description: 请自行添加对class描述
 **/
internal class MyRealChain<T : MyRequest, R : MyResult>(
    private val interceptors: List<MyInterceptor<T, R>>,
    private val index: Int,
    override val result: R,
    override val wrapper: MyWrapper<T, R>,
    private var opMpaasCall: MyCall<T, R>
) : MyInterceptor.Chain<T, R> {

    override fun proceed(wrapper: MyWrapper<T, R>): R {
        val next = MyRealChain(interceptors, index + 1, result, wrapper, opMpaasCall)
        val interceptor = interceptors[index]
//        println(" OpMpRealChain index = $index interceptor name = " + interceptor.javaClass.canonicalName)
        L.i(" OpMpRealChain index = $index 当前线程名称 = " + Thread.currentThread().name)
        return interceptor.intercept(next)
    }


}