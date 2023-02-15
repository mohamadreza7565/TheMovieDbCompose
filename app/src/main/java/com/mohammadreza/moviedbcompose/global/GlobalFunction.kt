package com.mohammadreza.moviedbcompose.global

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.net.Uri
import org.koin.androidx.compose.get
import org.koin.androidx.compose.inject
import org.koin.java.KoinJavaComponent

/**
 * Create by Mohammadreza Allahgholi
 *  Site: https://seniorandroid.ir
 */
object GlobalFunction {

    val isNetworkAvailable: Boolean
        get() {
            val mContext: Context by KoinJavaComponent.inject(Context::class.java)
            val connectivityManager =
                mContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            var activeNetworkInfo: NetworkInfo? = null
            activeNetworkInfo = connectivityManager.activeNetworkInfo
            return activeNetworkInfo != null && activeNetworkInfo.isConnected
        }



}