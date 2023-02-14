package com.mohammadreza.moviedbcompose.core.di

import android.content.Context

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.mohammadreza.moviedbcompose.core.di.interceptor.HeaderInterceptor
import com.mohammadreza.moviedbcompose.BuildConfig
import com.mohammadreza.moviedbcompose.core.di.interceptor.NetworkInterceptor
import com.mohammadreza.moviedbcompose.core.di.interceptor.OfflineInterceptor
import okhttp3.Cache
import okhttp3.OkHttpClient
import org.koin.java.KoinJavaComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.security.KeyStore
import java.security.SecureRandom
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.TrustManagerFactory
import javax.net.ssl.X509TrustManager
import javax.security.cert.CertificateException


/**
 * Create by Mohammadreza Allahgholi
 *  Site: https://seniorandroid.ir
 */


fun createBaseNetworkClient(gson: Gson) = retrofitClient(gson)

private fun retrofitClient(gson: Gson): Retrofit =
    Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .client(getOkHttpClient())
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

fun getOkHttpClient(): OkHttpClient {

    val mContext: Context by KoinJavaComponent.inject(Context::class.java)

    val okHttpClient = OkHttpClient.Builder()
        .cache(getCache(mContext))
        .addInterceptor(HeaderInterceptor())
        .addNetworkInterceptor(NetworkInterceptor())
        .addInterceptor(OfflineInterceptor())

    setTimeOutToOkHttpClient(okHttpClient)


    if (BuildConfig.DEBUG) {
             acceptUnsafeSsl(okHttpClient)
    }


    return okHttpClient.build()
}

private fun setTimeOutToOkHttpClient(okHttpClientBuilder: OkHttpClient.Builder) =
    okHttpClientBuilder.apply {
        readTimeout(90, TimeUnit.SECONDS)
        connectTimeout(90, TimeUnit.SECONDS)
        writeTimeout(90, TimeUnit.SECONDS)
    }



private fun getCache(mContext: Context) = Cache(getFile(mContext), 10 * 1000 * 1000) //10MB cache;

private fun getFile(mContext: Context) = File(mContext.cacheDir, "okhttp_cache").apply {
    mkdirs()
}

fun getGson() = GsonBuilder().create()

private fun acceptUnsafeSsl(okHttpClient: OkHttpClient.Builder) {
    // https://stackoverflow.com/a/63399149/5945169
    // Create a trust manager that does not validate certificate chains
    try {
        val trustAllCerts = arrayOf<TrustManager>(
            object : X509TrustManager {
                @Throws(CertificateException::class)
                override fun checkClientTrusted(
                    chain: Array<X509Certificate?>?,
                    authType: String?
                ) {
                }

                @Throws(CertificateException::class)
                override fun checkServerTrusted(
                    chain: Array<X509Certificate?>?,
                    authType: String?
                ) {
                }

                override fun getAcceptedIssuers(): Array<X509Certificate?>? {
                    return arrayOf()
                }
            }
        )

        // Install the all-trusting trust manager
        val sslContext = SSLContext.getInstance("SSL")
        sslContext.init(null, trustAllCerts, SecureRandom())

        // Create an ssl socket factory with our all-trusting manager
        val sslSocketFactory = sslContext.socketFactory
        val trustManagerFactory: TrustManagerFactory =
            TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm())
        trustManagerFactory.init(null as KeyStore?)
        val trustManagers: Array<TrustManager> = trustManagerFactory.trustManagers
        check(!(trustManagers.size != 1 || trustManagers[0] !is X509TrustManager)) {
            "Unexpected default trust managers:" + trustManagers.contentToString()
        }

        val trustManager = trustManagers[0] as X509TrustManager
        okHttpClient.sslSocketFactory(sslSocketFactory, trustManager)
        okHttpClient.hostnameVerifier { _, _ -> true }
    } catch (e: Exception) {
        throw RuntimeException(e)
    }
}