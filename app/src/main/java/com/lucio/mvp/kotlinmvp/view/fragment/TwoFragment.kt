package com.lucio.mvp.kotlinmvp.view.fragment

import android.os.Bundle
import android.view.View

import com.lucio.mvp.kotlinmvp.R
import com.lucio.mvp.kotlinmvp.base.BaseFragment

class TwoFragment : BaseFragment() {

    override fun onCreateView(): Int {
        return R.layout.fragment_two
    }

    override fun initView(contentView: View) {

    }

    override fun initData() {

    }

    override fun initListener() {

    }

    companion object {


        fun newInstance(): TwoFragment {
            val fragment = TwoFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }

}
