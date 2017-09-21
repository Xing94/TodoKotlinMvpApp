package com.lucio.mvp.kotlinmvp.presenter


import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.os.Handler
import android.os.Message
import android.support.v4.view.ViewPager

import com.lucio.mvp.kotlinmvp.R
import com.lucio.mvp.kotlinmvp.contract.MainContract
import com.lucio.mvp.kotlinmvp.data.bean.LoginBean
import com.lucio.mvp.kotlinmvp.util.LogUtil
import com.lucio.mvp.kotlinmvp.util.net.NetCallBack
import com.lucio.mvp.kotlinmvp.util.net.RetrofitUtil

/**
 * 主页面
 * 为什么不用tabHost：tabHost每一次切换都是replace，影响加载速度
 */
class MainPresenter(private val mView: MainContract.View) : MainContract.Presenter {

    override fun start() {

    }

    override fun netRequest() {

        RetrofitUtil.request(mView, RetrofitUtil.mainService.login("13122576190", "24cfeed0c1c1d9ed3414afc78d096b67"),
                object : NetCallBack<LoginBean>() {
                    override fun onSuccess(bean: LoginBean) {
                        val msg = mHandler.obtainMessage()
                        msg.obj = bean
                        msg.what = 101
                        mHandler.sendMessage(msg)
                        LogUtil.out(bean.toString())
                        mView.showToast("成功了")
                    }
                })
    }

    override fun selectFragment(radioId: Int) {
        when (radioId) {
            R.id.rb_one -> mView.selectFragment(0)
            R.id.rb_two -> mView.selectFragment(1)
            R.id.rb_three -> mView.selectFragment(2)
            R.id.rb_four -> mView.selectFragment(3)
        }
    }

    override fun getRadioDrawable(drawable: Drawable): Drawable {
        drawable.setBounds(0, 0, 50, 50)
        return drawable
    }

    override fun vpChangeListener(): ViewPager.OnPageChangeListener {
        return object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {
                mView.selectRadio(position)
            }

            override fun onPageScrollStateChanged(state: Int) {

            }
        }
    }

    //子线程的视图操作
    @SuppressLint("HandlerLeak")
    private val mHandler = object : Handler() {
        override fun handleMessage(msg: Message) {
            when (msg.what) {
                101 -> {
                }
            }
        }
    }
}
