package com.chaoyang.network

/**
 * @author: chaoyang
 * @create: 2019-12-03 17:40
 * @description: 请自行添加对class描述
 **/
interface Scheduler {

    fun isTargetThread(): Boolean {
        return true
    }

    fun post(runnable: Runnable) {
        runnable.run()
    }
}