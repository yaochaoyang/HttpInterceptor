package com.chaoyang.network.processor

import com.chaoyang.network.MyResult

/**
 * @author: chaoyang
 * @create: 2019-12-03 16:46
 * @description: 请自行添加对class描述
 **/
object EmptyPollingProcessor : PollingProcessor<MyResult> {
    override fun count(result: MyResult): Int = 0

    override fun interval(result: MyResult): Long = -1L

    override fun continuePolling(result: MyResult): Boolean = false
}
