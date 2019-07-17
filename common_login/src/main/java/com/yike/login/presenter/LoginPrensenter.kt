package com.yike.login.presenter

import com.xfhy.library.basekit.presenter.RxPresenter
import com.xfhy.library.rx.CommonSubscriber
import com.xfhy.library.rx.scheduler.SchedulerUtils
import com.yike.common_module.data.bean.BaseResp
import com.yike.login.data.LoginDataManager
import com.yike.login.entity.LoginRsp
import io.reactivex.Observable


/**
 * Author：wangcaiwen
 * Date：2019/4/3
 * Description：登录P
 */
class LoginPrensenter (val mView:LoginContract.View):RxPresenter(),LoginContract.Prensenter{

    override fun reqLogin(username: String, password: String, isLoading: Boolean) {
        if (isLoading){
            mView.showLoading()
        }

        addSubscribe(LoginDataManager
            .getLoginRsp(username, password)
            .compose(SchedulerUtils.ioToMain()).subscribeWith(object : CommonSubscriber<BaseResp<LoginRsp>?>(mView){
                override fun onNext(t: BaseResp<LoginRsp>?) {
                    super.onNext(t)
                    mView.loginSuccess(t)
                }
            }))
    }

}

