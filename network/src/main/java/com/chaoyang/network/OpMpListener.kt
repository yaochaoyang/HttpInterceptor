package com.chaoyang.network


/**
 * @author: chaoyang
 * @create: 2019-12-03 16:27
 * @description: 请自行添加对class描述
 **/
interface OpMpListener {

    fun onStart() {}

    fun onSuccess(result: OpMpResult) {}

    fun onFailure(cause: OpMpException) {}

    fun onSpecialStatus(cause: OpMpException) {}

    fun onFinish() {}
}