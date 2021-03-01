package com.chaoyang.network

/**
 * @Author: chaoyang
 * @CreateDate: 2/22/21 11:27 AM
 */
interface MyCallBack {
    fun onStart()

    fun onSuccess(result: MyResult)

    fun onFailure(cause: MyException)

    fun onSpecialStatus(cause: MyException)

    fun onFinish()
}