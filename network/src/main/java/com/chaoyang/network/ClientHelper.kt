package com.chaoyang.network

import com.chaoyang.network.interceptor.LoggerInterceptor
import com.chaoyang.network.interceptor.VerifyInterceptor
import com.chaoyang.network.interceptor.PollingInterceptor
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
        val opMpClient = MyClient(Executors.newCachedThreadPool())
            .addInterceptor(PollingInterceptor<MyRequest, MyResult>())
            .addInterceptor(VerifyInterceptor<MyRequest, MyResult>(client))
            .addInterceptor(LoggerInterceptor<MyRequest, MyResult>())
        MyExpress.init(opMpClient, SchedulerImpl());
    }
}
