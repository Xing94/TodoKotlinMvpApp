package com.lucio.mvp.kotlinmvp.util

import android.util.Log

import com.lucio.mvp.kotlinmvp.AppConstant

/**
 * 日志工具类
 */
object LogUtil {

    /**
     * @param tag 日志tag
     * *
     * @param msg 待打印信息
     */
    fun d(tag: String, msg: String?) {
        if (AppConstant.isDebug && msg != null) {
            Log.d(tag, msg)
        }
    }

    /**
     * error日志打印

     * @param tag 日志tag
     * *
     * @param msg 待打印信息
     */
    fun e(tag: String, msg: String?) {
        if (AppConstant.isDebug && msg != null) {
            Log.e(tag, msg)
        }
    }

    /**
     * error日志打印

     * @param e 待打印信息
     */
    fun e(e: Throwable?) {
        if (AppConstant.isDebug && e != null) {
            e.printStackTrace()
        }
    }

    /**
     * info日志打印

     * @param tag 日志tag
     * *
     * @param msg 待打印信息
     */
    fun i(tag: String, msg: String?) {
        if (AppConstant.isDebug && msg != null) {
            Log.i(tag, msg)
        }
    }

    /**
     * verbose日志打印

     * @param tag 日志tag
     * *
     * @param msg 待打印信息
     */
    fun v(tag: String, msg: String?) {
        if (AppConstant.isDebug && msg != null) {
            Log.v(tag, msg)
        }
    }

    /**
     * warn日志打印

     * @param tag 日志tag
     * *
     * @param msg 待打印信息
     */
    fun w(tag: String, msg: String?) {
        if (AppConstant.isDebug && msg != null) {
            Log.w(tag, msg)
        }
    }

    /**
     * System.out.println打印日志

     * @param msg
     */
    fun out(msg: String?) {
        if (AppConstant.isDebug && msg != null) {
            println(msg)
        }
    }
}
