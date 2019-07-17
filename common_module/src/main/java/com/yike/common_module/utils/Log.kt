package com.yike.common_module.utils

import android.util.Log


/**
 * Author：wangcaiwen
 * Date：2019/1/29
 * Description：
 */
private const val VERBOSE = 1
private const val DEBUG = 2
private const val INFO = 3
private const val WARN = 4
private const val ERROR = 5
private const val NOTHING = 6

//    private val level = VERBOSE;
private val level = YiKeFun.isDebug

/**
 * ?表示如果为空则不执行
 * !!表示如果为空则继续执行  可能会报空指针异常
 */
fun Any.logVerbose(msg: String?) {
    if (!level) Log.v(javaClass.simpleName, msg.toString())
}

fun Any.logDebug(msg: String?) {
    if (!level) Log.d(javaClass.simpleName, msg.toString())
}

fun Any.logInfo(msg: String?) {
    if (!level) Log.i(javaClass.simpleName, msg.toString())
}

fun Any.logWarn(msg: String?) {
    if (!level) Log.w(javaClass.simpleName, msg.toString())
}

fun Any.logError(msg: String?) {
    if (!level) Log.w(javaClass.simpleName, msg.toString())
}

/*
fun Any.logNothing(msg: String?) {
    if (!level) Log.(javaClass.simpleName, msg.toString())
}*/
