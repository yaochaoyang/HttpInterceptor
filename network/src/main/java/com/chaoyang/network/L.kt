package com.chaoyang.network

import android.util.Log
import kotlin.math.min

/**
 * Author: ycy
 * Date: 2019-05-21
 */
class L private constructor() {
    companion object {
        private const val TAG = "tag"
        private const val MAX_LENGTH = 1024 * 2

        const val NOTHING_THRESHOLD = Log.ASSERT + 100

        @JvmStatic
        internal var threshold: Int = Log.VERBOSE

        @JvmStatic
        fun v(msg: String) = v(TAG, msg)

        @JvmStatic
        fun d(msg: String) = d(TAG, msg)

        @JvmStatic
        fun i(msg: String) = i(TAG, msg)

        @JvmStatic
        fun w(msg: String) = w(TAG, msg)

        @JvmStatic
        fun e(msg: String) = e(TAG, msg)

        @JvmStatic
        fun v(tag: String, msg: String) = log(tag, msg, Log.VERBOSE)

        @JvmStatic
        fun d(tag: String, msg: String) = log(tag, msg, Log.DEBUG)

        @JvmStatic
        fun i(tag: String, msg: String) = log(tag, msg, Log.INFO)

        @JvmStatic
        fun w(tag: String, msg: String) = log(tag, msg, Log.WARN)

        @JvmStatic
        fun e(tag: String, msg: String) = log(tag, msg, Log.ERROR)

        @JvmStatic
        fun e(e: Throwable) {
            if (Log.ERROR >= threshold) {
                e.printStackTrace()
            }
        }

        @JvmStatic
        private fun log(tag: String, msg: String, level: Int) {
            if (level >= threshold) {
                println(tag, msg, level)
            }
        }

        @JvmStatic
        private fun println(tag: String, msg: String, priority: Int) {
            val length = msg.length
            if (length <= MAX_LENGTH) {
                Log.println(priority, tag, msg)
                return
            }
            var start = 0
            var end: Int
            while (start < length) {
                end = friendlyEnd(msg, start, min(start + MAX_LENGTH, length))
                Log.println(priority, tag, msg.substring(start, end))
                start = end
            }
        }

        @JvmStatic
        private fun friendlyEnd(msg: String, start: Int, end: Int): Int {
            if (msg.length == end || msg[end] == '\n') {
                return end
            }
            for (last in end - 1 downTo start + 1) {
                if (msg[last] == '\n') {
                    return last + 1
                }
            }
            return end
        }
    }
}
