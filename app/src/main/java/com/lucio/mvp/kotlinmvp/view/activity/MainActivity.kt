package com.lucio.mvp.kotlinmvp.view.activity

import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.widget.RadioButton
import android.widget.RadioGroup

import com.lucio.mvp.kotlinmvp.R
import com.lucio.mvp.kotlinmvp.base.BaseActivity
import com.lucio.mvp.kotlinmvp.constants.UrlConstant
import com.lucio.mvp.kotlinmvp.contract.MainContract
import com.lucio.mvp.kotlinmvp.presenter.MainPresenter
import com.lucio.mvp.kotlinmvp.view.adapter.FragmentListAdapter
import com.lucio.mvp.kotlinmvp.view.fragment.FourFragment
import com.lucio.mvp.kotlinmvp.view.fragment.OneFragment
import com.lucio.mvp.kotlinmvp.view.fragment.ThreeFragment
import com.lucio.mvp.kotlinmvp.view.fragment.TwoFragment
import org.jetbrains.anko.find
import org.jetbrains.anko.verticalLayout

import java.util.ArrayList

class MainActivity : BaseActivity(), MainContract.View {

    private var mPresenter: MainContract.Presenter? = null
    private var vpContent: ViewPager? = null

    private var rgFragment: RadioGroup? = null
    private var rbOne: RadioButton? = null
    private var rbTwo: RadioButton? = null
    private var rbThree: RadioButton? = null
    private var rbFour: RadioButton? = null

    private var pagerNameList: MutableList<String>? = null
    private var fragmentList: MutableList<Fragment>? = null

    override fun onCreate() {
        setContentView(R.layout.activity_main)

        mPresenter = initPresenter()

    }

    override val isShowActionBar: Boolean
        get() = true

    override fun initView() {

        vpContent = findViewById<ViewPager>(R.id.vp_content)
        rgFragment = findViewById<RadioGroup>(R.id.rg_fragment)

        rbOne = findViewById<RadioButton>(R.id.rb_one)
        rbTwo = findViewById<RadioButton>(R.id.rb_two)
        rbThree = findViewById<RadioButton>(R.id.rb_three)
        rbFour = findViewById<RadioButton>(R.id.rb_four)

    }

    override fun initData() {
        pagerNameList = ArrayList<String>()
        pagerNameList!!.add("主页")
        pagerNameList!!.add("搜索")
        pagerNameList!!.add("我的")
        pagerNameList!!.add("设置")

        fragmentList = ArrayList<Fragment>()
        fragmentList!!.add(OneFragment.newInstance())
        fragmentList!!.add(TwoFragment.newInstance())
        fragmentList!!.add(ThreeFragment.newInstance())
        fragmentList!!.add(FourFragment.newInstance())

        rbOne!!.text = pagerNameList!![0]
        rbOne!!.setCompoundDrawables(null, mPresenter!!.getRadioDrawable(resources.getDrawable(R.drawable.home_selector)), null, null)

        rbTwo!!.text = pagerNameList!![1]
        rbTwo!!.setCompoundDrawables(null, mPresenter!!.getRadioDrawable(resources.getDrawable(R.drawable.search_selector)), null, null)

        rbThree!!.text = pagerNameList!![2]
        rbThree!!.setCompoundDrawables(null, mPresenter!!.getRadioDrawable(resources.getDrawable(R.drawable.account_selector)), null, null)

        rbFour!!.text = pagerNameList!![3]
        rbFour!!.setCompoundDrawables(null, mPresenter!!.getRadioDrawable(resources.getDrawable(R.drawable.set_selector)), null, null)

        val mAdapter = FragmentListAdapter(supportFragmentManager, fragmentList!!)
        vpContent!!.adapter = mAdapter

        bar!!.title = pagerNameList!![0]
        //        设置标题栏左边的图标
        //        getBar().setHomeAsUpIndicator(R.drawable.ic_menu);
        //        设置标题栏左边的图标是否显示
        //        getBar().setDisplayHomeAsUpEnabled(true);

    }

    override fun initListener() {
        rgFragment!!.setOnCheckedChangeListener { _, i -> mPresenter!!.selectFragment(i) }
        vpContent!!.addOnPageChangeListener(mPresenter!!.vpChangeListener())
    }

    override fun onResume() {
        super.onResume()
        mPresenter!!.start()
    }

    override fun selectFragment(position: Int) {
        bar!!.title = pagerNameList!![position]
        vpContent!!.currentItem = position
    }

    override fun selectRadio(position: Int) {
        var radioId = R.id.rb_one
        if (position == 0)
            radioId = R.id.rb_one
        else if (position == 1)
            radioId = R.id.rb_two
        else if (position == 2)
            radioId = R.id.rb_three
        else if (position == 3) radioId = R.id.rb_four
        bar!!.title = pagerNameList!![position]
        rgFragment!!.check(radioId)
    }

    override fun initPresenter(): MainContract.Presenter {
        return MainPresenter(this)
    }

}
