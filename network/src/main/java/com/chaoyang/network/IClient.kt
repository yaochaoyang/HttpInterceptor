package com.chaoyang.network

import android.app.Application

/**
 * Author: ycy
 * Date: 2019-05-05
 */
interface IClient : ActivityObservable {
    val context: Application
}