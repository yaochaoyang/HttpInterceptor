package com.chaoyang.network

import android.content.Context
import android.os.Handler
import android.os.Looper

/**
 * Author: ycy
 * Date: 2019-08-01
 **/
class DefaultVerifier {
    private val handler = Handler(Looper.getMainLooper())

    fun startVerify(
        context: Context,
        params: Map<String, Any>,
        listener: VerifyListener
    ) {
        if (Looper.getMainLooper() === Looper.myLooper()) {
            verify(context, params, listener)
        } else {
            handler.post { verify(context, params, listener) }
        }
    }

    private fun verify(context: Context, params: Map<String, Any>, listener: VerifyListener) {
        if (true) {
            listener.onResult("Result")
        } else {
            listener.onAction("Action")
        }
    }

}