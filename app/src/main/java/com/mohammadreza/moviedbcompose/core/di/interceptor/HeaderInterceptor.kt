package com.mohammadreza.moviedbcompose.core.di.interceptor


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
        val original = chain.request()

        val builder1 = original.newBuilder()


        val request = builder1.build()

        return chain.proceed(request)
    }


}
