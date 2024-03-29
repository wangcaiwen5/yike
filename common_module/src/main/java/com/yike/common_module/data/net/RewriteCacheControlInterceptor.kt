
package com.yike.common_module.data.net
import android.content.Context
import android.text.TextUtils
import com.yike.common_module.utils.NetWorkUtils
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

/**
 * Author：wangcaiwen
 * Date：2019/1/29
 * Description：拦截器
 * 有网络,走网络请求数据
 * 无网络,则从缓存加载数据
 */
class RewriteCacheControlInterceptor(private val mContext: Context) : Interceptor {

    companion object {
        /**
         * 无网络,设缓存有效期为两周
         */
        private val CACHE_STALE_SEC = (60 * 60 * 24 * 14).toLong()
        /**
         * 有网 缓存60s
         * 测试-----------------改为0,为了及时看到效果
         */
        private val MAX_AGE: Long = 0
    }

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val cacheControl = request.cacheControl().toString()
        if (!NetWorkUtils.isNetWorkAvailable(mContext)) {
            request = request.newBuilder()
                    .cacheControl(if (TextUtils.isEmpty(cacheControl))
                        CacheControl.FORCE_CACHE
                    else
                        CacheControl.FORCE_NETWORK)
                    .build()
        }
        val originalResponse = chain.proceed(request)
        return if (NetWorkUtils.isNetWorkAvailable(mContext)) {
            //有网的时候连接服务器请求,缓存60s
            originalResponse.newBuilder()
                    .header("Cache-Control", "public, max-age=" + MAX_AGE)
                    .removeHeader("Pragma")
                    .build()
        } else {
            //网络断开时读取缓存
            originalResponse.newBuilder()
                    .header("Cache-Control", "public, only-if-cached, max-stale=" + CACHE_STALE_SEC)
                    .removeHeader("Pragma")
                    .build()
        }
    }

}
