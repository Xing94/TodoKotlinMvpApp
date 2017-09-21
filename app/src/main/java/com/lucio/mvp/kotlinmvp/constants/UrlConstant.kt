package com.lucio.mvp.kotlinmvp.constants

import retrofit2.http.POST

/**
 * 接口常量类
 *
 * 接口需要添加const关键字
 */

interface UrlConstant {

    companion object {
        const val login: String="SleepDetector/client/user/login"
    }

}
