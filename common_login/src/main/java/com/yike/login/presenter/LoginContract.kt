package com.yike.login.presenter

import com.xfhy.library.basekit.presenter.BasePresenter
import com.xfhy.library.basekit.view.BaseView
import com.yike.common_module.data.bean.BaseResp
import com.yike.login.entity.LoginRsp
import io.reactivex.Observable


/**
 * Author：wangcaiwen
 * Date：2019/4/3
 * Description：登录契约类
 */
interface LoginContract {
    interface Prensenter : BasePresenter {
        fun reqLogin(username: String, password: String, isLoading: Boolean)
    }

    interface View : BaseView {
        fun loginSuccess(baseResp: BaseResp<LoginRsp>?)
    }

}