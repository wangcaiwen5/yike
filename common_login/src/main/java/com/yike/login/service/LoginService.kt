package com.yike.login.service

import com.yike.common_module.data.bean.BaseResp
import com.yike.login.entity.LoginRsp
import io.reactivex.Flowable
import io.reactivex.Observable
import retrofit2.http.POST
import retrofit2.http.Query


/**
 * Author：wangcaiwen
 * Date：2019/4/3
 * Description：登录注册模块
 */
interface LoginService {
    @POST("user/login")
    fun loginRequest(@Query("username") username: String,
                     @Query("password") password: String): Flowable<BaseResp<LoginRsp>>


}