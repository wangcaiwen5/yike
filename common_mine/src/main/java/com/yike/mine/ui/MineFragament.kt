package com.yike.mine.ui

import android.os.Bundle
import com.yike.common_module.base.BaseFragment
import com.yike.mine.R

/**
 * Author：wangcaiwen
 * Date：2019/3/21
 * Description：
 */
class MineFragament : BaseFragment() {
    companion object {
        private lateinit var mTitle: String

        fun newInstance(title: String): MineFragament {
            val fragment = MineFragament()
            val ages = Bundle()
            this.mTitle = title
            fragment.arguments = ages
            return fragment
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_mine
    }

    override fun initView() {
    }

    override fun lazyLoad() {
    }

}
