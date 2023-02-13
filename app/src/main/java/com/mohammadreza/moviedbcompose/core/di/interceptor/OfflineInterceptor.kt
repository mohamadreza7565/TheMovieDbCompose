package com.mohammadreza.moviedbcompose.core.di.interceptor
import android.util.Log
import com.mohammadreza.moviedbcompose.global.GlobalFunction
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.util.concurrent.TimeUnit


/**
 * Create by Mohammadreza Allahgholi
 *  Site: https://seniorandroid.ir
 */

internal class OfflineInterceptor : Interceptor {
    private val HEADER_CACHE_CONTROL = "Cache-Control"
    private val HEADER_PRAGMA = "Pragma"

    override fun intercept(chain: Interceptor.Chain): Response {
        Log.d("TAG", "offline interceptor: called.")
        var request: Request = chain.request()

        // prevent caching when network is on. For that we use the "networkInterceptor"
        if (!GlobalFunction.isNetworkAvailable) {
            val cacheControl: CacheControl = CacheControl.Builder()
                .maxStale(30, TimeUnit.DAYS)
                .build()

            request = request.newBuilder()
                .removeHeader(HEADER_PRAGMA)
                .removeHeader(HEADER_CACHE_CONTROL)
                .cacheControl(cacheControl)
                .build()
        }
        return chain.proceed(request)
    }
}
