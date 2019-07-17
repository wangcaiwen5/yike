package com.yike.home.service

import com.yike.common_module.data.bean.BaseResp
import com.yike.home.entity.HomeListRsp
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path


/**
 * Author：wangcaiwen
 * Date：2019/3/29
 * Description：
 */
interface HomeService {
    /**
     *首页文章列表,pageNum  为页码
     */
    @GET("article/list/{pageNum}/json")
    fun getHomeList(@Path("pageNum") pageNum: String): Observable<BaseResp<HomeListRsp>>

}