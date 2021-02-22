package com.chaoyang.network

import android.app.Application

/**
 * @Author: chaoyang
 * @CreateDate: 2/22/21 1:19 PM
 * @UpdateRemark: Modify the description
 */
open class BaseApplication : Application(), IClient {
    override val context: Application
        get() = this

    override fun onCreate() {
        super.onCreate()
        ClientHelper.initialize(this)
    }

    override fun registerActivityObserver(observer: ActivityObserver) {
        registerActivityLifecycleCallbacks(observer)
    }

    override fun unregisterActivityObserver(observer: ActivityObserver) {
        unregisterActivityLifecycleCallbacks(observer)
    }
}