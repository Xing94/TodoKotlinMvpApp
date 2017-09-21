package com.lucio.mvp.kotlinmvp.util

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.res.Resources
import android.support.annotation.StringRes
import android.widget.Toast

import com.lucio.mvp.kotlinmvp.MvpApp

/**
 * Toast工具类：单例模式
 */
@SuppressLint("StaticFieldLeak")
object ToastUtil {

    private var mToast: Toast? = null
    private var mAppContext: Context? = null

    fun initAppContext(appContext: Context) {
        mAppContext = appContext
    }

    fun show(msg: String) {
        if (mAppContext == null) {
            return
        }
        showToast(mAppContext!!, msg, Toast.LENGTH_SHORT)
    }

    fun show(@StringRes msg: Int) {
        if (mAppContext == null) {
            return
        }
        showToast(mAppContext!!, MvpApp.context!!.getResources().getString(msg), Toast.LENGTH_SHORT)
    }

    /**
     * 短时间文本提示
     * 避免在子线程中调用时出问题

     * @param activity 上下文
     * *
     * @param msg      待提示信息
     */
    fun showShortToast(activity: Activity, msg: String) {
        activity.runOnUiThread { showToast(activity, msg, Toast.LENGTH_SHORT) }
    }

    /**
     * 短时间文本提示
     * 显示在主线程中

     * @param activity 上下文
     * *
     * @param msg      待提示信息
     */
    fun showShortToast(activity: Activity, @StringRes msg: Int) {
        activity.runOnUiThread { showToast(activity, activity.resources.getString(msg), Toast.LENGTH_SHORT) }
    }

    /**
     * 短时间文本提示

     * @param context 上下文
     * *
     * @param msg     待提示信息
     */
    fun showLongToast(context: Context, msg: String) {
        showToast(context, msg, Toast.LENGTH_LONG)
    }

    /**
     * 短时间文本提示

     * @param context 上下文
     * *
     * @param msg     待提示信息
     */
    fun showLongToast(context: Context, @StringRes msg: Int) {
        var toastStr = ""
        try {
            toastStr = context.resources.getString(msg)
        } catch (e: Resources.NotFoundException) {
            toastStr = toastStr + msg
        }

        showToast(context, toastStr, Toast.LENGTH_LONG)
    }

    /**
     * @param context
     * *
     * @param msg
     * *
     * @param duration
     */
    fun showToast(context: Context, msg: String, duration: Int) {
        if (mToast != null) {
            mToast!!.cancel()
        }
        mToast = Toast.makeText(context, msg, duration)
        mToast!!.show()
    }


}
