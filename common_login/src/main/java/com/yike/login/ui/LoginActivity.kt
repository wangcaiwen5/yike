package com.yike.login.ui


import com.alibaba.android.arouter.facade.annotation.Route
import com.arouter.annotations.BindPath
import com.yike.common_module.base.TitleBarMvpActivity
import com.yike.common_module.data.bean.BaseResp
import com.yike.login.R
import com.yike.login.entity.LoginRsp
import com.yike.login.presenter.LoginContract
import com.yike.login.presenter.LoginPrensenter
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.toast
@BindPath("login/login")
class LoginActivity : TitleBarMvpActivity<LoginPrensenter>(), LoginContract.View {


    /**
     * "?"加在变量名后，系统在任何情况不会报它的空指针异常。
    "!!"加在变量名后，如果对象为null，那么系统一定会报异常！
     */
    override fun loginSuccess(baseResp: BaseResp<LoginRsp>?) {
        val errorCode = baseResp?.errorCode
        val errorMsg = baseResp?.errorMsg
        errorMsg?.let { toast(it) }

    }

    override fun initPresenter() {
        mPresenter = LoginPrensenter(this)
       // mPresenter?.mView=this

    }

    override fun initData() {
    }

    override fun initListener() {
        bt_login.setOnClickListener {
            mPresenter?.reqLogin(et_username.text.toString().trim(),et_password.text.toString().trim(),true)
        }
    }

    override fun initView() {
    }

    override fun getLayoutResId(): Int = R.layout.activity_login


}
