package com.chaoyang.network


/**
 * @author: chaoyang
 * @create: 2019-12-03 16:27
 * @description: 请自行添加对class描述
 **/
interface MyListener {

    fun onStart() {}

    fun onSuccess(result: MyResult) {}

    fun onFailure(cause: MyException) {}

    fun onSpecialStatus(cause: MyException) {}

    fun onFinish() {}
}