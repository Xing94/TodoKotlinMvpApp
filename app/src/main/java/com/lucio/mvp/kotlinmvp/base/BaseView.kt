package com.lucio.mvp.kotlinmvp.base

import android.support.annotation.StringRes

/**
 * view的基类
 */
interface BaseView<T> {

    fun initPresenter(): T

    fun showToast(msg: String)

    fun showToast(@StringRes msg: Int)

}