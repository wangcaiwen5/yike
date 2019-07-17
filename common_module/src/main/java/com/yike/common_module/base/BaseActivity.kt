package com.yike.common_module.base

import android.app.Activity
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Toast
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.yike.common_module.utils.ActivityManager
import com.yike.common_module.widget.MultipleStatusView
import io.reactivex.annotations.NonNull
import pub.devrel.easypermissions.AppSettingsDialog
import pub.devrel.easypermissions.EasyPermissions
import java.lang.ref.WeakReference
import android.os.Build
import android.view.WindowManager






/**
 * Author：wangcaiwen
 * Date：2019/1/29
 * Description：
 */
abstract class BaseActivity : AppCompatActivity(), EasyPermissions.PermissionCallbacks {

    /**
     * 判断当前Activity是否在前台。
     */
    protected var isActive: Boolean = false

    private var weakReference: WeakReference<Activity>? = null
    /**
     * 当前Activity的实例。
     */
    protected var activity: Activity? = null

    /**
     * 多种状态的View 切换
     */
    protected var mLayoutStatusView: MultipleStatusView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResId())
        hideBottomUIMenu(this)
        activity = this
        weakReference = WeakReference(this)
        ActivityManager.add(weakReference)
        mLayoutStatusView?.setOnClickListener(mRetryClickListener)
        initView()//初始化View
        initListener()//初始化事件
        initData()//初始化数据
    }

    private val mRetryClickListener: View.OnClickListener = View.OnClickListener {
    }

    override fun onResume() {
        super.onResume()
        isActive = true
    }

    override fun onDestroy() {
        super.onDestroy()
        activity = null
        ActivityManager.remove(weakReference)
    }

    abstract fun initData()//初始化数据

    abstract fun initListener()//初始化监听

    abstract fun initView()//初始化View

    abstract fun getLayoutResId(): Int //获取View



    /**
     * 重写要申请权限的Activity或者Fragment的onRequestPermissionsResult()方法，
     * 在里面调用EasyPermissions.onRequestPermissionsResult()，实现回调。
     *
     * @param requestCode  权限请求的识别码
     * @param permissions  申请的权限
     * @param grantResults 授权结果
     */
    override fun onRequestPermissionsResult(requestCode: Int, @NonNull permissions: Array<String>, @NonNull grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }

    /**
     * 当权限被成功申请的时候执行回调
     *
     * @param requestCode 权限请求的识别码
     * @param perms       申请的权限的名字
     */
    override fun onPermissionsGranted(requestCode: Int, perms: List<String>) {
        Log.i("EasyPermissions", "获取成功的权限$perms")
    }

    /**
     * 当权限申请失败的时候执行的回调
     *
     * @param requestCode 权限请求的识别码
     * @param perms       申请的权限的名字
     */
    override fun onPermissionsDenied(requestCode: Int, perms: List<String>) {
        //处理权限名字字符串
        val sb = StringBuffer()
        for (str in perms) {
            sb.append(str)
            sb.append("\n")
        }
        sb.replace(sb.length - 2, sb.length, "")
        //用户点击拒绝并不在询问时候调用
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            Toast.makeText(this, "已拒绝权限" + sb + "并不再询问", Toast.LENGTH_SHORT).show()
            AppSettingsDialog.Builder(this)
                .setRationale("此功能需要" + sb + "权限，否则无法正常使用，是否打开设置")
                .setPositiveButton("好")
                .setNegativeButton("不行")
                .build()
                .show()
        }
    }

    /**
     * 隐藏虚拟按键
     */
    fun hideBottomUIMenu(activity: Activity) {
        val window = window
        val params = window.attributes
        params.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        window.attributes = params
    }

}