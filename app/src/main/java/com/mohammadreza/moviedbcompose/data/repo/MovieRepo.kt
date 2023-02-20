package com.mohammadreza.moviedbcompose.data.repo

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.mohammadreza.moviedbcompose.core.base.BaseDataSource
import com.mohammadreza.moviedbcompose.data.api.MovieApiService
import com.mohammadreza.moviedbcompose.data.database.RoomAppDatabase
import com.mohammadreza.moviedbcompose.data.database.dao.MovieDao
import com.mohammadreza.moviedbcompose.data.model.MovieLikeModel
import com.mohammadreza.moviedbcompose.ui.screens.popular.PopularPagingSource

/**
 * Create by Mohammadreza Allahgholi
 *  Site: https://seniorandroid.ir
 */
class MovieRepo(
    private val mMovieApiService: MovieApiService,
    private val movieDao : MovieDao,
) : BaseDataSource() {

    fun getPopular() = Pager(
        config = PagingConfig(
            pageSize = 5,
        ),
        pagingSourceFactory = {
            PopularPagingSource(mMovieApiService)
        }
    ).flow

    fun getMovieDetails(id: Int) = callApi { mMovieApiService.getMovieDetails(id) }

   suspend fun doLike(id: Int) {

        movieDao.get(id)?.let {
            MovieLikeModel(
                id = id,
                isLiked = true
            ).also {
                movieDao.like(it)
            }
        } ?: kotlin.run {
            MovieLikeModel(
                id = id,
                isLiked = true
            ).also {
                movieDao.like(it)
            }

        }

    }

    suspend fun isLiked(id: Int) = movieDao.get(id)?.isLiked ?: false

}