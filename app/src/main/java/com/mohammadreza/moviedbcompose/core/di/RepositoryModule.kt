package com.mohammadreza.moviedbcompose.core.di

import com.mohammadreza.moviedbcompose.data.repo.MovieRepo
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

/**
 * Create by Mohammadreza Allahgholi
 *  Site: https://seniorandroid.ir
 */
val repositoryModule = module {
    single {
        MovieRepo(
            mMovieApiService = get(),
            movieDao = get ()
        )
    }
}