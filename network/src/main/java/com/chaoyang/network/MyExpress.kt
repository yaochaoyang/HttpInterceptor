package com.chaoyang.network


/**
 * @author: chaoyang
 * @create: 2019-12-03 17:26
 * @description: 请自行添加对class描述
 **/
object MyExpress {
    private lateinit var client: MyClient
    private lateinit var scheduler: Scheduler

    fun init(client: MyClient, scheduler: Scheduler) {
        MyExpress.client = client
        MyExpress.scheduler = scheduler
    }

    fun <T : MyRequest, R : MyResult> pack(_package: MyPackage<T, R>): MyExecutor<T, R> {
        return DefaultExecutor(client.newCall(_package.wrapper, _package.result), scheduler)
    }

}
