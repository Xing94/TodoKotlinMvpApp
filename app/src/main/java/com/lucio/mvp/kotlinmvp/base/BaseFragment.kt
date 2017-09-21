package com.lucio.mvp.kotlinmvp.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * fragment基类
 */

abstract class BaseFragment : Fragment() {

    private var rootView: View? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (rootView == null) {
            rootView = inflater!!.inflate(onCreateView(), container, false)

            //初始化view
            initView(rootView!!)

            //初始化数据
            initData()

            //初始化点击事件
            initListener()
        }

        (rootView!!.parent as ViewGroup?)?.removeView(rootView)
        return rootView
    }

    /**
     * 设置布局
     */
    abstract fun onCreateView(): Int

    /**
     * 初始化view,工作:主要是用来对view相关操作,比如查找控件等
     */
    abstract fun initView(contentView: View)

    /**
     * 初始化数据
     */
    abstract fun initData()

    /**
     * 初始化事件监听
     */
    abstract fun initListener()


}
