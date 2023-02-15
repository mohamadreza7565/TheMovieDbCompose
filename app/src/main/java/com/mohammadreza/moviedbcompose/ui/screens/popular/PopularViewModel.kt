package com.mohammadreza.moviedbcompose.ui.screens.popular

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.mohammadreza.moviedbcompose.core.base.BaseApiDataState
import com.mohammadreza.moviedbcompose.core.base.BaseViewModel
import com.mohammadreza.moviedbcompose.data.model.MovieModel
import com.mohammadreza.moviedbcompose.data.model.PopularModel
import com.mohammadreza.moviedbcompose.data.repo.MovieRepo
import kotlinx.coroutines.launch
import java.util.concurrent.Flow

/**
 * Create by Mohammadreza Allahgholi
 *  Site: https://seniorandroid.ir
 */

class PopularViewModel(private val mMovieRepo: MovieRepo) : BaseViewModel() {

    var movies: LazyPagingItems<MovieModel>? = null

    fun getPopular() = mMovieRepo.getPopular().cachedIn(viewModelScope)


}