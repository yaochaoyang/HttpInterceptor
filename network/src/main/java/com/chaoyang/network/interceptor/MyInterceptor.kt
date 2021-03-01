package com.chaoyang.network.interceptor

import com.chaoyang.network.MyRequest
import com.chaoyang.network.MyResult
import com.chaoyang.network.MyWrapper


/**
 * @author: chaoyang
 * @create: 2019-12-03 16:54
 * @description: 请自行添加对class描述
 **/
interface MyInterceptor<T : MyRequest, R : MyResult> {

    fun intercept(chain: Chain<T, R>): R

    interface Chain<T : MyRequest, R : MyResult> {

        val result: R

        val wrapper: MyWrapper<T, R>

        fun proceed(wrapper: MyWrapper<T, R>): R
    }
}