package com.lucio.mvp.kotlinmvp.util.net


import com.lucio.mvp.kotlinmvp.R
import com.lucio.mvp.kotlinmvp.base.BaseBean
import com.lucio.mvp.kotlinmvp.base.BaseView
import com.lucio.mvp.kotlinmvp.util.LogUtil

/**
 * 网络请求回调函数
 */
abstract class NetCallBack<T> {

    abstract fun onSuccess(bean: T)

    fun onFailed(mView: BaseView<*>, bean: BaseBean) {
        if (bean.reMsg != null) {
            if (!bean.reMsg!!.isEmpty()) {
                mView.showToast(bean.reMsg!!)
            } else {
                mView.showToast(R.string.request_fail)
            }
        } else {
            mView.showToast(R.string.request_fail)
        }
    }

    fun onError(mView: BaseView<*>, e: Throwable) {
        mView.showToast(R.string.network_unavailable)
        LogUtil.out("onError")
        e.printStackTrace()

    }

    fun onCompleted() {
        LogUtil.out("onCompleted")
    }

}
