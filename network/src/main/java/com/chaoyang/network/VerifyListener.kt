package com.chaoyang.network;

/**
 * Author: ycy
 * Date: 2019-07-31
 **/
interface VerifyListener {
    fun onResult(result: String) {}

    fun onAction(action: String) {}
}