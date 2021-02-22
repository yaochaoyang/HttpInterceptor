package com.chaoyang.network

/**
 * @author: chaoyang
 * @create: 2019-12-03 16:46
 * @description: 请自行添加对class描述
 **/
interface ActivityObservable {
    fun registerActivityObserver(observer: ActivityObserver)

    fun unregisterActivityObserver(observer: ActivityObserver)
}