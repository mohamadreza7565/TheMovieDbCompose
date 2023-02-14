package com.mohammadreza.moviedbcompose.data.repo

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.mohammadreza.moviedbcompose.core.base.BaseDataSource
import com.mohammadreza.moviedbcompose.data.api.MovieApiService
import com.mohammadreza.moviedbcompose.ui.screens.popular.PopularPagingSource

/**
 * Create by Mohammadreza Allahgholi
 *  Site: https://seniorandroid.ir
 */
class MovieRepo(private val mMovieApiService: MovieApiService) : BaseDataSource() {

   fun getPopular() = Pager(
       config = PagingConfig(
           pageSize = 5,
       ),
       pagingSourceFactory = {
           PopularPagingSource(mMovieApiService)
       }
   ).flow

    fun getMovieDetails(id: Int) = callApi { mMovieApiService.getMovieDetails(id) }

}