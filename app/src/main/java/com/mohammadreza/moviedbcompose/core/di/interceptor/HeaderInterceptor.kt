package com.mohammadreza.moviedbcompose.core.di.interceptor


import com.mohammadreza.moviedbcompose.BuildConfig
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException


/**
 * Create by Mohammadreza Allahgholi
 *  Site: https://seniorandroid.ir
 */
internal class HeaderInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        var original = chain.request()

        val url: HttpUrl = original.url.newBuilder().addQueryParameter("api_key", BuildConfig.API_KEY).build()

        original = original.newBuilder().url(url).build()

        val builder1 = original.newBuilder()

        val request = builder1.build()

        return chain.proceed(request)
    }


}
