package com.chaoyang.network

import com.chaoyang.network.interceptor.LoggerInterceptor
import com.chaoyang.network.interceptor.OpMpVerifyInterceptor
import com.chaoyang.network.interceptor.OpMpPollingInterceptor
import java.util.concurrent.Executors

/**
 * Author: ycy
 * Date: 2019-05-05
 */
object ClientHelper {
    lateinit var client: IClient
        private set

    fun initialize(client: IClient) {
        this.client = client
        initOpMpExpress(this.client)
    }


    private fun initOpMpExpress(client: IClient) {
        val opMpClient = OpMpClient(client, Executors.newCachedThreadPool())
            .addInterceptor(OpMpPollingInterceptor<OpMpRequest, OpMpResult>())
            .addInterceptor(OpMpVerifyInterceptor<OpMpRequest, OpMpResult>(client))
            .addInterceptor(LoggerInterceptor<OpMpRequest, OpMpResult>())
        OpMpExpress.init(opMpClient, SchedulerImpl());
    }
}
