package com.chaoyang.network

/**
 * @author: chaoyang
 * @create: 2019-12-03 11:54
 * @description: 请自行添加对class描述
 **/
interface OpMpExecutor<T : OpMpRequest, R : OpMpResult> {

    fun cancel(): Boolean

    fun execute(): R

    fun enqueue(listener: OpMpListener)

}