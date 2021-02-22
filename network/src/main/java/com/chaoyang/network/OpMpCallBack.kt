package com.chaoyang.network

/**
 * @Author: chaoyang
 * @CreateDate: 2/22/21 11:27 AM
 */
interface OpMpCallBack {
    fun onStart()

    fun onSuccess(result: OpMpResult)

    fun onFailure(cause: OpMpException)

    fun onSpecialStatus(cause: OpMpException)

    fun onFinish()
}