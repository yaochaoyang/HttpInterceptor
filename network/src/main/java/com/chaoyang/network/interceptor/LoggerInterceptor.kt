package com.chaoyang.network.interceptor

import com.chaoyang.network.L
import com.chaoyang.network.MyRequest
import com.chaoyang.network.MyResult
import com.chaoyang.network.toJson
import java.lang.StringBuilder

/**
 * @author: chaoyang
 * @create: 2019-12-10 15:38
 * @description: 请自行添加对class描述
 *
 **/
class LoggerInterceptor<T : MyRequest, R : MyResult> : MyInterceptor<T, R> {
    companion object {
        const val TAG = "LoggerCore"
    }

    override fun intercept(chain: MyInterceptor.Chain<T, R>): R {
        val start = System.currentTimeMillis()
        val elapse = System.currentTimeMillis() - start
        val result = chain.proceed(chain.wrapper)
        val stringBuilder = StringBuilder()
        buildLog(stringBuilder, chain.wrapper.request, result, elapse)
        printLog(stringBuilder.toString())
        return result
    }

    private fun printLog(log: String) {
        L.i(TAG, log)
    }

    private fun buildLog(builder: StringBuilder, request: T, response: R, elapse: Long) {
        builder.append(" \n").append(request::class.java.canonicalName)
            .append(" --------------- ").append(elapse).append(" mills ------------>")
            .append('\n').append(toJson(request))
            .append("\n\n\n").append(toJson(response))
            .append(response::class.java.canonicalName)
            .append('\n')

    }
}