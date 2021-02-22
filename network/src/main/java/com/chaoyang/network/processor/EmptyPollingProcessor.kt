package com.chaoyang.network.processor

import com.chaoyang.network.OpMpResult

/**
 * @author: chaoyang
 * @create: 2019-12-03 16:46
 * @description: 请自行添加对class描述
 **/
object EmptyPollingProcessor : PollingProcessor<OpMpResult> {
    override fun count(result: OpMpResult): Int = 0

    override fun interval(result: OpMpResult): Long = -1L

    override fun continuePolling(result: OpMpResult): Boolean = false
}
