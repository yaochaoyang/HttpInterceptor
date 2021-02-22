package com.chaoyang.network

import com.alibaba.fastjson.JSON
import java.lang.reflect.Type

/**
 * Author: ycy
 * Date: 2019-06-16
 */
fun <T> fromJson(json: String, clazz: Class<T>): T {
    return JSON.parseObject(json, clazz)
}

inline fun <reified T> fromJson(json: String): T {
    return JSON.parseObject(json, T::class.java)
}

fun <T> fromJson(json: String, typeOfT: Type): T? {
    return JSON.parseObject(json, typeOfT)
}

fun <T> fromJsonArray(json: String, clazz: Class<T>): MutableList<T> {
    return JSON.parseArray(json, clazz)
}

inline fun <reified T> fromJsonArray(json: String): MutableList<T> {
    return JSON.parseArray(json, T::class.java)
}

fun toJson(any: Any): String {
    return JSON.toJSONString(any)
}

fun toFormattedJson(any: Any): String {
    return JSON.toJSONString(any, true)
}