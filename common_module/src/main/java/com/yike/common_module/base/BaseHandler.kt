package com.yike.common_module.base

import android.app.Activity
import android.os.Handler
import android.os.Message
import android.support.v4.app.Fragment
import com.yike.common_module.utils.What
import com.yike.common_module.utils.logDebug
import java.lang.ref.WeakReference

/**
 * Author：wangcaiwen
 * Date：2019/3/15
 * Description：
 */
abstract class BaseHandler : Handler {

    protected var activityWeakReference: WeakReference<Activity>? = null
    protected var fragmentWeakReference: WeakReference<Fragment>? = null

    constructor(activity: Activity) {
        this.activityWeakReference = WeakReference(activity)
    }

    constructor(fragment: Fragment) {
        this.fragmentWeakReference = WeakReference(fragment)
    }


    override fun handleMessage(msg: Message?) {
        super.handleMessage(msg)
        if (activityWeakReference == null || activityWeakReference?.get() == null || activityWeakReference?.get()!!.isFinishing) {
            //activity不可用
            logDebug("activity is gone")
            handleMessage(msg, What.ACTIVITY_GONE)
        } else if (fragmentWeakReference == null || fragmentWeakReference?.get() == null || fragmentWeakReference?.get()!!.isRemoving) {
            //fragment不可用
            logDebug("activity is gone")
            handleMessage(msg, What.ACTIVITY_GONE)
        } else {
            //对象A ?: 对象B
            //?:表示的意思是，当对象A值为null的时候，那么它就会返回后面的对象B。
            handleMessage(msg, msg?.what ?: -1)
        }


    }

    private fun handleMessage(msg: Message?, activitY_GONE: Int) {

    }

}
