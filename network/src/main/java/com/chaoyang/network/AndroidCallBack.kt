package com.chaoyang.network

/**
 * @author: chaoyang
 * @create: 2019-12-03 19:03
 * @description: 请自行添加对class描述
 **/
class AndroidCallBack(
    private val scheduler: Scheduler,
    private val listener: MyListener
) : MyCallBack {

    override fun onStart() {
        dispatch { listener.onStart() }
    }

    override fun onSuccess(result: MyResult) {
        dispatch { listener.onSuccess(result) }
    }

    override fun onFailure(cause: MyException) {
        dispatch { listener.onFailure(cause) }
    }

    override fun onSpecialStatus(cause: MyException) {
        dispatch { listener.onSpecialStatus(cause) }
    }

    override fun onFinish() {
        dispatch { listener.onFinish() }
    }

    private fun dispatch(block: () -> Unit) {
        if (scheduler.isTargetThread()) {
            block()
        } else {
            scheduler.post(Runnable { block() })
        }
    }
}