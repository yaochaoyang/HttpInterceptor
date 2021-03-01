package com.chaoyang.httpinterceptor

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.chaoyang.network.*
import com.chaoyang.network.interceptor.LoggerInterceptor

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        testNetwork()
    }

    private fun testNetwork() {
        val loginRequest = LoginRequest("test","123")
        val LoginResult = LoginResult("ssss")
        MyPackage(loginRequest,LoginResult)
            .addInterceptor(LoggerInterceptor())
            .getExecutor().enqueue(object :MyListener{
            override fun onStart() {
                super.onStart()
                L.i("onStart")
            }

            override fun onSuccess(result: MyResult) {
                super.onSuccess(result)
                L.i("onSuccess")
            }

            override fun onFailure(cause: MyException) {
                super.onFailure(cause)
                L.i("onFailure")
            }

            override fun onFinish() {
                super.onFinish()
                L.i("onFinish")
            }

        })
    }
}