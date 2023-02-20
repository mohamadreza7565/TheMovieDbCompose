package com.mohammadreza.moviedbcompose.core.di


import com.mohammadreza.moviedbcompose.data.api.MovieApiService
import com.mohammadreza.moviedbcompose.data.database.RoomAppDatabase
import com.mohammadreza.moviedbcompose.data.database.createDataBaseInstance
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext
import org.koin.core.context.GlobalContext.get
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

val databaseModule = module {
    single { createDataBaseInstance(context = androidContext()) }
    single { get<RoomAppDatabase>().getMovieDao() }
}


val gsonModule = module {
    single { mGson }
}


val networkModule = module {
    single { movieApiService }
}
