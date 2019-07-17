package com.yike.login.data


import com.yike.common_module.BuildConfig
import com.yike.common_module.data.bean.BaseResp
import com.yike.common_module.data.net.RetrofitFactory
import com.yike.login.entity.LoginRsp
import com.yike.login.service.LoginService
import io.reactivex.Flowable
import io.reactivex.Observable


/**
 * Author：wangcaiwen
 * Date：2019/4/3
 * Description：
 */
object LoginDataManager {
    private val mRetrofitFactory: RetrofitFactory by lazy { RetrofitFactory.instance }

    private val loginService: LoginService
        get() = mRetrofitFactory.create(
            LoginService::class.java,
            BuildConfig.BASE_URL
        )


    fun getLoginRsp(username: String, password: String): Flowable<BaseResp<LoginRsp>> {
        return loginService.loginRequest(username, password)
    }
}