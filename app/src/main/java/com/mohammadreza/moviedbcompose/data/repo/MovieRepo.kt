package com.mohammadreza.moviedbcompose.data.repo

import com.mohammadreza.moviedbcompose.core.base.BaseDataSource
import com.mohammadreza.moviedbcompose.data.api.MovieApiService

/**
 * Create by Mohammadreza Allahgholi
 *  Site: https://seniorandroid.ir
 */
class MovieRepo(private val mMovieApiService: MovieApiService) : BaseDataSource() {

    fun getPopular() = callApi { mMovieApiService.getPopularMovies() }

    fun getMovieDetails(id: Int) = callApi { mMovieApiService.getMovieDetails(id) }

}