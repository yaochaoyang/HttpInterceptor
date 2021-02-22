package com.chaoyang.network

/**
 * @author: chaoyang
 * @create: 2019-12-03 19:03
 * @description: 请自行添加对class描述
 **/
class AndroidOpMpCallBack(
    private val scheduler: Scheduler,
    private val listener: OpMpListener
) : OpMpCallBack {

    override fun onStart() {
        dispatch { listener.onStart() }
    }

    override fun onSuccess(result: OpMpResult) {
        dispatch { listener.onSuccess(result) }
    }

    override fun onFailure(cause: OpMpException) {
        dispatch { listener.onFailure(cause) }
    }

    override fun onSpecialStatus(cause: OpMpException) {
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