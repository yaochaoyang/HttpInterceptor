package com.chaoyang.network

import android.os.Handler
import android.os.Looper

/**
 * @author: chaoyang
 * @create: 2019-12-11 17:31
 * @description: 请自行添加对class描述
 **/
class SchedulerImpl : Scheduler {
    private companion object {
        val handler: Handler = Handler(Looper.getMainLooper())
    }

    override fun isTargetThread(): Boolean {
        return Looper.myLooper() === Looper.getMainLooper()
    }

    override fun post(runnable: Runnable) {
        handler.post(runnable)
    }
}