package com.yike.home.ui

import android.os.Bundle
import com.yike.common_module.base.BaseFragment
import com.yike.home.R


/**
 * Author：wangcaiwen
 * Date：2019/3/20
 * Description：
 */
class HomeFragment : BaseFragment() {



    companion object {

        private val PAGER_COUNT = 1
        private lateinit var mTitle: String


        fun newInstance(title:String): HomeFragment {
            val args = Bundle()
            val fragment = HomeFragment()
            fragment.arguments = args
            this.mTitle=title
            return fragment
        }
    }


    override fun getLayoutId(): Int {
       return R.layout.fragment_home
    }

    override fun initView() {

    }

    override fun lazyLoad() {

    }
}