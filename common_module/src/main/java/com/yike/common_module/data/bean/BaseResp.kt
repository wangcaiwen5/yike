package com.yike.common_module.data.bean
/**
 * @author xfhy
 * time create at 2018/1/28 16:30
 * description 自己服务器 通用 返参 res
 */
 class BaseResp<out T>(val errorCode: Int, val errorMsg: String, val data: T? = null)
