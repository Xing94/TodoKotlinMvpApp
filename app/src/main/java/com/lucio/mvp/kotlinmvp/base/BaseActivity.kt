package com.lucio.mvp.kotlinmvp.base

import android.content.Context
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.support.annotation.StringRes
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import android.widget.Toast

import com.lucio.mvp.kotlinmvp.R
import com.lucio.mvp.kotlinmvp.util.ToastUtil

/**
 * activity基类
 */

abstract class BaseActivity : AppCompatActivity() {

    //获取ActionBar：不设置的话默认显示的是activity的label
    var bar: ActionBar? = null
        private set

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        onCreate()
        if (isShowActionBar) {
            initActionBar()
        }
        initView()
        initData()
        initListener()
    }

    //做setContentView等操作
    abstract fun onCreate()

    //初始化ActionBar
    fun initActionBar() {
        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        bar = supportActionBar
    }

    abstract val isShowActionBar: Boolean

    //初始化视图
    abstract fun initView()

    //初始化数据
    abstract fun initData()

    //初始化事件监听
    abstract fun initListener()

    fun showToast(msg: String) {
        ToastUtil.showShortToast(this, msg)
    }

    fun showToast(@StringRes msg: Int) {
        ToastUtil.showShortToast(this, msg)
    }

    /**
     * 初始化状态栏高度设置
     * 暂时不使用
     */
    private fun initStatusBar() {
        // 判断当前系统版本号是否大于4.4,如果小于,则不继续进行
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) return

        // 设置透明状态栏
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.requestFeature(Window.FEATURE_NO_TITLE)

        // 由于4.4-5.5和5.0以上两个版本的透明状态栏有不同的设置方法,因此需要进行不同版本的适配
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            val contentView = findViewById<View>(android.R.id.content) as ViewGroup
            val statusBarView = View(this)
            val lp = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    getStatusBarHeight(this))
            statusBarView.setBackgroundColor(Color.TRANSPARENT)
            contentView.addView(statusBarView, lp)
        }

        // 系统版本大于5.0
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window = window
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS or WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = Color.TRANSPARENT
        }
    }

    /**
     * 获取状态栏高度

     * @param context 上下文
     * *
     * @return 状态栏高度
     */
    fun getStatusBarHeight(context: Context): Int {
        var result = 0
        val resourceId = context.resources.getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId > 0) {
            result = context.resources.getDimensionPixelSize(resourceId)
        }
        return result
    }

}
