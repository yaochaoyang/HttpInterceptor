package com.chaoyang.network

import android.app.Activity
import android.app.Application
import android.os.Bundle

/**
 * @author: chaoyang
 * @create: 2019-12-03 16:46
 * @description: 请自行添加对class描述
 **/
interface ActivityObserver : Application.ActivityLifecycleCallbacks {
    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
    }

    override fun onActivityStarted(activity: Activity) {
    }

    override fun onActivityResumed(activity: Activity) {
    }

    override fun onActivityPaused(activity: Activity) {
    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
    }

    override fun onActivityStopped(activity: Activity) {
    }

    override fun onActivityDestroyed(activity: Activity) {
    }
}