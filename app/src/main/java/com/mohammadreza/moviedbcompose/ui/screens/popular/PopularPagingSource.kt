package com.mohammadreza.moviedbcompose.ui.screens.popular

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.mohammadreza.moviedbcompose.core.base.BaseApiDataState
import com.mohammadreza.moviedbcompose.data.api.MovieApiService
import com.mohammadreza.moviedbcompose.data.model.MovieModel
import com.mohammadreza.moviedbcompose.data.model.PopularModel
import com.mohammadreza.moviedbcompose.data.repo.MovieRepo

class PopularPagingSource(
    private val mMovieApiService: MovieApiService,
) : PagingSource<Int, MovieModel>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieModel> {
        try {
            val page = params.key ?: 1
            val response = mMovieApiService.getPopularMovies(page = page).body()

            response?.let {
                return LoadResult.Page(
                    data = response.movies,
                    prevKey = if (page == 1) null else page.minus(1),
                    nextKey = if (response.movies.isEmpty()) null else page.plus(1),
                )
            } ?: kotlin.run {
                return LoadResult.Error(Exception(""))
            }

        } catch (e: Exception) {
            return LoadResult.Error(Exception(""))
        }

    }

    override fun getRefreshKey(state: PagingState<Int, MovieModel>): Int {
        return ((state.anchorPosition ?: 0) - state.config.initialLoadSize / 2)
            .coerceAtLeast(0)
    }
}
