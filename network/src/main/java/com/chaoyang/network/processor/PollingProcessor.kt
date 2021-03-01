package com.chaoyang.network.processor

import com.chaoyang.network.MyResult

/**
 * @author: chaoyang
 * @create: 2019-12-03 16:46
 * @description: 请自行添加对class描述
 **/
interface PollingProcessor<R : MyResult> {

    fun count(result: R): Int = 15

    /**
     * milliseconds
     */
    fun interval(result: R): Long = 1000L

    fun continuePolling(result: R): Boolean
}