package com.lucio.mvp.kotlinmvp.util

import android.annotation.SuppressLint
import android.content.Context

/**
 * dp、px、sp转换工具类
 */
@SuppressLint("StaticFieldLeak")
object DensityUtil {

    private val appContext: Context? = null

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    fun dip2px(context: Context, dpValue: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (dpValue * scale + 0.5f).toInt()
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    fun px2dip(context: Context, pxValue: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (pxValue / scale + 0.5f).toInt()
    }
}