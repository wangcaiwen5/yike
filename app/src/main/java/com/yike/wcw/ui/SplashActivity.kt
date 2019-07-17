package com.yike.wcw.ui


import android.app.PendingIntent.getActivity
import android.graphics.drawable.BitmapDrawable
import android.os.Handler
import android.os.Message
import android.view.View
import com.alibaba.android.arouter.launcher.ARouter
import com.arouter.annotations.BindPath
import com.yike.arouter.Arouter
import com.yike.common_module.base.BaseActivity
import com.yike.wcw.R
import kotlinx.android.synthetic.main.activity_lunch.*
import java.lang.ref.WeakReference

@BindPath("login/Splash")
class SplashActivity : BaseActivity() {

    private var handler: Handler = WithoutLeakHandler(this)

    companion object {
        const val GET_LOCAL_VIDEOS: Int = 100

        /**
         * by moosphon on 2018/09/16
         * desc: 解决handler内存泄漏的问题，消息的处理需要放在内部类的{@link #Handler.handleMessage}
         */
        private class WithoutLeakHandler(activity: SplashActivity) : Handler() {
            private var splashActivity: WeakReference<SplashActivity> = WeakReference(activity)

            override fun handleMessage(msg: Message) {
                super.handleMessage(msg)
                when (msg.what) {
                    GET_LOCAL_VIDEOS -> {
                        val activity = splashActivity.get()


                    }
                }
            }
        }
    }

    override fun initData() {


    }

    override fun initListener() {
        handler.postDelayed({
            //iv_bg.setImageResource(R.drawable.logo)
            //layout.setBackgroundColor(resources.getColor(R.color.colorPrimary))
            // ARouter.getInstance().build("/login/user").navigation()

            // finish()
            Arouter.getInstance().jumpActivity("login/login", null)
        }, 2000)
        layout.setOnClickListener {

            // Toast.makeText(this,"TIAO",Toast.LENGTH_LONG).show()
        }
    }

    override fun initView() {

    }


    fun jump(view: View) {

        Arouter.getInstance().jumpActivity("login/login", null)
    }

    override fun getLayoutResId(): Int = R.layout.activity_lunch


    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacksAndMessages(null)
    }

}
