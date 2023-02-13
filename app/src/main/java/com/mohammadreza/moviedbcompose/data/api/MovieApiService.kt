package com.mohammadreza.moviedbcompose.data.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Create by Mohammadreza Allahgholi
 *  Site: https://seniorandroid.ir
 */
interface MovieApiService {

    @GET("movie/popular")
    suspend fun getPopularMovies(): Response<String>

    @GET("movie/{id}")
    suspend fun getMovieDetails(
        @Path("id") id: Int
    ): Response<String>

}