package com.chaoyang.network


/**
 * @author: chaoyang
 * @create: 2019-12-03 17:26
 * @description: 请自行添加对class描述
 **/
object OpMpExpress {
    private lateinit var client: OpMpClient
    private lateinit var scheduler: Scheduler

    fun init(client: OpMpClient, scheduler: Scheduler) {
        OpMpExpress.client = client
        OpMpExpress.scheduler = scheduler
    }

    fun <T : OpMpRequest, R : OpMpResult> pack(_package: OpMpPackage<T, R>): OpMpExecutor<T, R> {
        return DefaultOpMpExecutor(client.newCall(_package.wrapper, _package.result), scheduler)
    }

}
