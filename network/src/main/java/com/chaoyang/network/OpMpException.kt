package com.chaoyang.network

/**
 * @author: chaoyang
 * @create: 2019-12-03 16:29
 * @description: 请自行添加对class描述
 **/
open class OpMpException(
    val status: String,
    message: String,
    cause: Throwable? = null,
    val result: OpMpResult? = null
) : RuntimeException(message, cause)