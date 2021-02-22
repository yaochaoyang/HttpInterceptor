package com.chaoyang.network

/**
 * @author: chaoyang
 * @create: 2019-12-03 16:33
 * @description: 请自行添加对class描述
 **/
interface OpMpCall<T : OpMpRequest, R : OpMpResult> {

    val wrapper: OpMpWrapper<T, R>

    val result: R

    fun cancel(): Boolean

    fun execute(): R

    fun enqueue(callBack: OpMpCallBack)
}