package com.yike.wcw.ui

import android.support.v4.app.FragmentTransaction
import com.alibaba.android.arouter.facade.annotation.Route
import com.flyco.tablayout.listener.CustomTabEntity
import com.flyco.tablayout.listener.OnTabSelectListener
import com.yike.common_module.base.BaseActivity
import com.yike.find.FindFragment
import com.yike.home.ui.HomeFragment
import com.yike.knowledge.ui.KnowledgeFragment
import com.yike.mine.ui.MineFragament
import com.yike.wcw.R
import com.yike.wcw.entity.TabEntity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

@Route(path = "/main/home")
class MainActivity : BaseActivity() {


    private val mTitles = arrayOf("首页", "知识", "发现", "我的")
    //选中icon
    private val mIconSelectIds = intArrayOf(
        R.drawable.home_select,
        R.drawable.knowledge_select,
        R.drawable.find_select,
        R.drawable.mine_select
    )
    //未选中icon
    private val mIconUnSelectIds = intArrayOf(
        R.drawable.home_normal,
        R.drawable.knowledge_normal,
        R.drawable.find_normal,
        R.drawable.mine_normal
    )

    //by lazy用于属性延迟初始化
    private val mHomeFragment by lazy { HomeFragment.newInstance(mTitles[0]) }
    private val mKnowledgeFragment by lazy { KnowledgeFragment.newInstance(mTitles[1]) }
    private val mFindFragment by lazy { FindFragment.newInstance(mTitles[2]) }
    private val mMineFragament by lazy { MineFragament.newInstance(mTitles[3]) }

    private val mTabEntities = ArrayList<CustomTabEntity>()

    //默认的索引
    private var mIndex = 0


    override fun initData() {
        initTab()
    }

    private fun initTab() {
        (0 until mTitles.size)
            .mapTo(mTabEntities) { TabEntity(mTitles[it], mIconSelectIds[it], mIconUnSelectIds[it]) }

        tab_layout.setTabData(mTabEntities)

        tab_layout.currentTab = mIndex//设置默认的tab
        switchFragment(mIndex)

    }

    private fun switchFragment(position: Int) {
        val beginTransaction = supportFragmentManager.beginTransaction()
        hideFragents(beginTransaction)
        when (position) {
            0 -> mHomeFragment.let {
                when {
                    it.isAdded -> beginTransaction.show(it)
                    else -> {
                        beginTransaction.add(R.id.fl_container, it, "home").show(it)
                    }
                }

            }
            1 -> mKnowledgeFragment.let {
                when {
                    it.isAdded -> beginTransaction.show(it)
                    else -> {
                        beginTransaction.add(R.id.fl_container, it, "knowledge").show(it)
                    }
                }

            }
            2 -> mFindFragment.let {
                when {
                    it.isAdded -> beginTransaction.show(it)
                    else -> {
                        beginTransaction.add(R.id.fl_container, it, "find").show(it)
                    }
                }

            }
            3 -> mMineFragament.let {
                when {
                    it.isAdded -> beginTransaction.show(it)
                    else -> {
                        beginTransaction.add(R.id.fl_container, it, "mine").show(it)
                    }
                }
            }
            else -> {

            }
        }
        mIndex = position
        tab_layout.currentTab = mIndex
        beginTransaction.commitAllowingStateLoss()

    }

    /**
     * 隐藏所以的fragment
     */
    private fun hideFragents(beginTransaction: FragmentTransaction) {
        mHomeFragment.let { beginTransaction.hide(it) }
        mKnowledgeFragment.let { beginTransaction.hide(it) }
        mFindFragment.let { beginTransaction.hide(it) }
        mMineFragament.let { beginTransaction.hide(it) }

    }

    override fun initListener() {
        tab_layout.setOnTabSelectListener(object : OnTabSelectListener {
            override fun onTabSelect(position: Int) {
                switchFragment(position)
            }


            override fun onTabReselect(position: Int) {
            }

        })
    }

    override fun initView() {

    }

    override fun getLayoutResId(): Int = R.layout.activity_main


}
