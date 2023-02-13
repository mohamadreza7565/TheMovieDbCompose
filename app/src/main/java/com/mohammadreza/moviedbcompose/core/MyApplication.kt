package com.mohammadreza.moviedbcompose.core

import android.app.Application
import com.mohammadreza.moviedbcompose.core.di.gsonModule
import com.mohammadreza.moviedbcompose.core.di.networkModule
import com.mohammadreza.moviedbcompose.core.di.repositoryModule
import com.mohammadreza.moviedbcompose.core.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/**
 * Create by Mohammadreza Allahgholi
 *  Site: https://seniorandroid.ir
 */
class MyApplication : Application() {


    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MyApplication)
            modules(
                listOf(gsonModule, networkModule, repositoryModule, viewModelModule)
            )
        }

    }

}