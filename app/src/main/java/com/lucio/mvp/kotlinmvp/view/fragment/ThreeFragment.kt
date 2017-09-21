package com.lucio.mvp.kotlinmvp.view.fragment

import android.os.Bundle
import android.view.View

import com.lucio.mvp.kotlinmvp.R
import com.lucio.mvp.kotlinmvp.base.BaseFragment

class ThreeFragment : BaseFragment() {

    override fun onCreateView(): Int {
        return R.layout.fragment_three
    }

    override fun initView(contentView: View) {

    }

    override fun initData() {

    }

    override fun initListener() {

    }

    companion object {


        fun newInstance(): ThreeFragment {
            val fragment = ThreeFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }

}
