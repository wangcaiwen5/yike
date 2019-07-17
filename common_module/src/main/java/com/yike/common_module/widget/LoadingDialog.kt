package com.yike.common_module.widget

import android.app.Dialog
import android.content.Context
import android.graphics.drawable.AnimationDrawable
import android.view.Gravity
import android.widget.ImageView
import com.yike.common_module.R

/**
 * Created by xfhy on 2018/2/4 16:09
 * Description : 加载对话框
 */
class LoadingDialog private constructor(context: Context, theme: Int) : Dialog(context, theme) {

    companion object {
        private lateinit var mDialog: LoadingDialog
        private var loadingViewDrawable: AnimationDrawable? = null

        fun create(context: Context): LoadingDialog {
            mDialog = LoadingDialog(context, R.style.LightProgressDialog)
            mDialog.setContentView(R.layout.loading_view)
            mDialog.setCancelable(true)
            //点击外面是否可取消
            mDialog.setCanceledOnTouchOutside(false)
            mDialog.window.attributes.gravity = Gravity.CENTER

            val lp = mDialog.window.attributes
            lp.dimAmount = 0.2f
            mDialog.window.attributes = lp

          //  loadingViewDrawable = mDialog.find<ImageView>(R.id.iv_loading).background as AnimationDrawable
            return mDialog
        }
    }

    /**
     * 显示对话框
     */
    override fun show() {
        super.show()
        loadingViewDrawable?.start()
    }

    /**
     * 隐藏对话框
     */
    override fun hide() {
        mDialog.dismiss()
        loadingViewDrawable?.stop()
    }

}