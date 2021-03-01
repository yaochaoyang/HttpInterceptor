package com.chaoyang.network.processor

import com.chaoyang.network.MyRequest
import com.chaoyang.network.MyResult

/**
 * @author: chaoyang
 * @create: 2019-12-03 16:46
 * @description: 请自行添加对class描述
 **/
interface VerifyProcessor<T : MyRequest, R : MyResult> {

    fun needVerify(request: T, result: R): Boolean {
        return false
    }

    fun buildVerifyParams(request: T, result: R): Map<String, String> {
        return emptyMap()
    }

    fun processRequest(request: T, flag: Boolean) {
    }

    fun needDoubleVerify(boolean: Boolean)

    fun isNeedDoubleVerify(): Boolean
}