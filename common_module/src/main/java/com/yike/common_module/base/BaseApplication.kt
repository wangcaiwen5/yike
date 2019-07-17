package com.yike.common_module.base

import android.app.Application
import com.yike.common_module.data.net.OkHttpUtils


/**
 * Author：wangcaiwen
 * Date：2019/3/14
 * Description：BaseApplication
 */
open class BaseApplication : Application() {
    private lateinit var application: BaseApplication
    override fun onCreate() {
        super.onCreate()
        application = this
        initOkHttp()

    }



    private fun initOkHttp() {
        OkHttpUtils.initOkHttp(application)
    }

    /*override fun getApplicationContext(): Context {
        return super.getApplicationContext()
    }*/

    private fun getApplicationInstance(): BaseApplication {
        return application
    }

}