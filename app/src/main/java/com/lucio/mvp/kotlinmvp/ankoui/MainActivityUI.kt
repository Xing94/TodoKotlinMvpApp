package com.lucio.mvp.kotlinmvp.ankoui

import com.lucio.mvp.kotlinmvp.view.activity.MainActivity
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.relativeLayout

/**
 * 三种写法
 */
class MainActivityUI : AnkoComponent<MainActivity> {

//    override fun createView(ui: AnkoContext<MainActivity>): View {
//        return ui.apply {
//
//        }.view
//    }

//    override fun createView(ui: AnkoContext<MainActivity>) = ui.apply {
//
//    }.view

    override fun createView(ui: AnkoContext<MainActivity>) = with(ui) {
        relativeLayout {

        }
    }


}