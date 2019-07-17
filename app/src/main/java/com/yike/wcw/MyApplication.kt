package com.yike.wcw

import android.support.multidex.MultiDex
import com.alibaba.android.arouter.launcher.ARouter
import com.squareup.leakcanary.LeakCanary
import com.yike.arouter.Arouter
//import com.alibaba.android.arouter.launcher.ARouter
import com.yike.common_module.base.BaseApplication
import com.yike.common_module.utils.YiKeFun

/**
 * Author：wangcaiwen
 * Date：2019/3/14
 * Description：
 */
class MyApplication : BaseApplication() {

    override fun onCreate() {
        super.onCreate()
        initMultiDex()
        initLeak()
        initARouter()

    }

    private fun initMultiDex() {
        //突破65535的限制
        MultiDex.install(this)

    }

    private fun initARouter() {
        val debug = YiKeFun.isDebug
        //ARouter配置
        if (debug) {           // 这两行必须写在init之前，否则这些配置在init过程中将无效
            ARouter.openLog()     // 打印日志
            ARouter.openDebug()   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(this) // 尽可能早，推荐在Application中初始化
        Arouter.getInstance().init(this)

    }


    private fun initLeak() {

        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return
        }
        LeakCanary.install(this)
        // Normal app init code...
    }
}
