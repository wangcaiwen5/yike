package com.yike.find

import android.os.Bundle
import com.yike.common_module.base.BaseFragment


/**
 * Author：wangcaiwen
 * Date：2019/3/21
 * Description：发现页面
 */
class FindFragment : BaseFragment() {

    companion object {

        private lateinit var mTitle: String

        fun newInstance(title: String): FindFragment {
            val args = Bundle()
            val fragment = FindFragment()
            fragment.arguments = args
            this.mTitle = title
            return fragment
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_find
    }

    override fun initView() {
    }

    override fun lazyLoad() {
    }
}