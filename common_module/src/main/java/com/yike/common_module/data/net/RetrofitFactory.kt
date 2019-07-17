package com.yike.common_module.data.net

import com.yike.common_module.data.net.OkHttpUtils
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Author：wangcaiwen
 * Date：2019/1/29
 * Description：retrofit工厂
 */
class RetrofitFactory private constructor() {

    companion object {
        val instance: RetrofitFactory by lazy { RetrofitFactory() }
    }

    /**
     * 具体服务实例化
     */
    fun <T> create(service: Class<T>, baseUrl: String): T {
        val retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(OkHttpUtils.okHttpClient)
                .build()
        return retrofit.create(service)
    }

}