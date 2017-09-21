package com.lucio.mvp.kotlinmvp.contract


import android.graphics.drawable.Drawable
import android.support.v4.view.ViewPager

import com.lucio.mvp.kotlinmvp.base.BasePresenter
import com.lucio.mvp.kotlinmvp.base.BaseView

/**
 * 契约接口：签订view与presenter之间的关系
 */

interface MainContract {

    interface View : BaseView<Presenter> {
        fun selectFragment(position: Int)

        fun selectRadio(position: Int)
    }

    interface Presenter : BasePresenter {

        fun netRequest()

        fun selectFragment(radioId: Int)

        fun getRadioDrawable(drawable: Drawable): Drawable

        fun vpChangeListener(): ViewPager.OnPageChangeListener
    }

}
