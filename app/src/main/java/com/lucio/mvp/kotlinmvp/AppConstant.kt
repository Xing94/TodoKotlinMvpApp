package com.lucio.mvp.kotlinmvp

/**
 * App常用公共常量
 */

object AppConstant {

    //是否在在调试状态:用于控制log、服务器环境等
    var isDebug = true

    //测试环境服务器
    private val DEBUG_HOST = "http://healthsleep.onethird.com.cn/"

    //正式环境服务器
    private val RELEASE_HOST = ""

    //获取当前使用的服务器地址
    val host: String
        get() = if (isDebug) DEBUG_HOST else RELEASE_HOST
}
