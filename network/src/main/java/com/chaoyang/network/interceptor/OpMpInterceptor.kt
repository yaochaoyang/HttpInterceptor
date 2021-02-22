package com.chaoyang.network.interceptor

import com.chaoyang.network.OpMpRequest
import com.chaoyang.network.OpMpResult
import com.chaoyang.network.OpMpWrapper


/**
 * @author: chaoyang
 * @create: 2019-12-03 16:54
 * @description: 请自行添加对class描述
 **/
interface OpMpInterceptor<T : OpMpRequest, R : OpMpResult> {

    fun intercept(chain: Chain<T, R>): R

    interface Chain<T : OpMpRequest, R : OpMpResult> {

        val result: R

        val wrapper: OpMpWrapper<T, R>

        fun proceed(wrapper: OpMpWrapper<T, R>): R
    }
}