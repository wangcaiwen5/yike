package com.yike.knowledge.ui

import android.os.Bundle
import com.yike.common_module.base.BaseFragment
import com.yike.knowledge.R

/**
 * Author：wangcaiwen
 * Date：2019/3/21
 * Description：
 */
class KnowledgeFragment : BaseFragment() {

    companion object {
        private lateinit var mTitle: String

        fun newInstance(title: String): KnowledgeFragment {

            val args = Bundle()
            val fragment = KnowledgeFragment()
            fragment.arguments=args
            this.mTitle=title
            return fragment

        }
    }

    override fun getLayoutId(): Int {
       return R.layout.fragment_knowledge
    }

    override fun initView() {

    }

    override fun lazyLoad() {
    }

}
