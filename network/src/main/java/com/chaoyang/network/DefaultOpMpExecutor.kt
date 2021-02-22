package com.chaoyang.network

/**
 * @author: chaoyang
 * @create: 2019-12-03 16:32
 * @description: 请自行添加对class描述
 **/
class DefaultOpMpExecutor<T : OpMpRequest, R : OpMpResult>(
        private val call: OpMpCall<T, R>,
        private val scheduler: Scheduler
) : OpMpExecutor<T, R> {
    override fun cancel(): Boolean {
        return call.cancel()
    }

    override fun execute(): R {
        return call.execute()
    }

    override fun enqueue(listener: OpMpListener) {
        call.enqueue(AndroidOpMpCallBack(scheduler = scheduler, listener = listener))
    }

}