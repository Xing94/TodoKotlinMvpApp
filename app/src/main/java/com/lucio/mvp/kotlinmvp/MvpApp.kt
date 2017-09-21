package com.lucio.mvp.kotlinmvp

import android.app.Application
import android.content.Context


import com.lucio.mvp.kotlinmvp.util.ToastUtil
import com.lucio.mvp.kotlinmvp.util.net.RetrofitUtil

import java.util.concurrent.TimeUnit

import okhttp3.OkHttpClient

class MvpApp : Application() {

    override fun onCreate() {
        super.onCreate()

        context = this

        ToastUtil.initAppContext(context!!)

        mClient = OkHttpClient.Builder()
                .retryOnConnectionFailure(true)
                //                .readTimeout(0, TimeUnit.SECONDS)
                .connectTimeout(9, TimeUnit.SECONDS)
                .build()

        RetrofitUtil.setBaseUrl(AppConstant.host)
    }

    companion object {

        var context: Context? = null
            private set
        private var mClient: OkHttpClient? = null

        //                    .readTimeout(0, TimeUnit.SECONDS)
        val client: OkHttpClient
            get() {
                if (mClient == null) {
                    mClient = OkHttpClient.Builder()
                            .retryOnConnectionFailure(true)
                            .connectTimeout(9, TimeUnit.SECONDS)
                            .build()
                }
                return mClient!!
            }
    }
}
