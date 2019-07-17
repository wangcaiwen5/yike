package com.yike.common_module.utils

import android.app.Activity
import java.lang.ref.WeakReference
import java.util.*


/**
 * Author：wangcaiwen
 * Date：2019/1/29
 * Description：
 */
object ActivityManager {
    private const val TAG = "ActivityManager"
    private val activityList = ArrayList<WeakReference<Activity>?>()

    fun size(): Int {
        logDebug("")
        return activityList.size
    }

    fun add(activity: WeakReference<Activity>?){
        activityList.add(activity)
    }

    fun remove(activity: WeakReference<Activity>?){
        val remove = activityList.remove(activity);
        logDebug("activity移除:$remove")
    }

    fun finishAll(){
        if(activityList.isNotEmpty()){
            for (weakReference in activityList) {
                val activity = weakReference?.get()
                if (activity!=null&&!activity.isFinishing){
                    activity.finish()
                }
            }
            activityList.clear()
        }
    }


}