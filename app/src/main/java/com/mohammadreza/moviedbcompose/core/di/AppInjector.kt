package com.mohammadreza.moviedbcompose.core.di


import com.mohammadreza.moviedbcompose.data.api.MovieApiService
import org.koin.dsl.module
import retrofit2.Retrofit

/**
 *
 * Usage: this methods handle DI
 *
 * How to call: just startActivityForResult koin in application class
 *
 */
private val mGson = getGson()
val baseRetrofit: Retrofit = createBaseNetworkClient(gson = mGson)
val movieApiService: MovieApiService = baseRetrofit.create(MovieApiService::class.java)

val gsonModule = module {
    single { mGson }
}


val networkModule = module {
    single { movieApiService }
}
