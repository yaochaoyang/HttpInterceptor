package com.chaoyang.network

/**
 * @author: chaoyang
 * @create: 2019-12-03 16:32
 * @description: 请自行添加对class描述
 **/
class DefaultExecutor<T : MyRequest, R : MyResult>(
    private val call: MyCall<T, R>,
    private val scheduler: Scheduler
) : MyExecutor<T, R> {
    override fun cancel(): Boolean {
        return call.cancel()
    }

    override fun execute(): R {
        return call.execute()
    }

    override fun enqueue(listener: MyListener) {
        call.enqueue(AndroidCallBack(scheduler = scheduler, listener = listener))
    }

}