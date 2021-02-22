package com.chaoyang.network.processor

import com.chaoyang.network.L
import com.chaoyang.network.OpMpRequest
import com.chaoyang.network.OpMpResult

/**
 * @author: chaoyang
 * @create: 2019-12-03 17:04
 * @description: 请自行添加对class描述
 **/
class DefaultVerifyProcessor<T : OpMpRequest, R : OpMpResult> : VerifyProcessor<T, R> {
    private var doubleVerify = true

    override fun needVerify(request: T, result: R): Boolean {
        super.needVerify(request, result)
        //TODO something
        return false
    }

    override fun buildVerifyParams(request: T, result: R): Map<String, String> {
       return super.buildVerifyParams(request, result)
    }

    override fun processRequest(request: T, flag: Boolean) {
        super.processRequest(request, flag)
        L.i("DefaultVerifyProcessor processRequest =  $flag")
    }


    override fun needDoubleVerify(boolean: Boolean) {
        doubleVerify = boolean
    }

    override fun isNeedDoubleVerify(): Boolean = doubleVerify

}