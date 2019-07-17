package com.yike.wcw.entity

import com.flyco.tablayout.listener.CustomTabEntity

class TabEntity(val title: String, val selectIcon: Int, val UNSelectIcon: Int) : CustomTabEntity {
    override fun getTabUnselectedIcon(): Int {
        return UNSelectIcon
    }

    override fun getTabSelectedIcon(): Int {
        return selectIcon
    }

    override fun getTabTitle(): String {
        return title
    }

}
