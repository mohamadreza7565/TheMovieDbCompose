package com.mohammadreza.moviedbcompose.ui.screens.popular

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.mohammadreza.moviedbcompose.core.base.BaseApiDataState
import com.mohammadreza.moviedbcompose.core.base.BaseViewModel
import com.mohammadreza.moviedbcompose.data.model.PopularModel
import com.mohammadreza.moviedbcompose.data.repo.MovieRepo
import kotlinx.coroutines.launch

/**
 * Create by Mohammadreza Allahgholi
 *  Site: https://seniorandroid.ir
 */

class PopularViewModel(private val mMovieRepo: MovieRepo) : BaseViewModel() {

    var popularMovies by mutableStateOf<BaseApiDataState<PopularModel>>(BaseApiDataState.Loading)

    fun getPopular() {

        viewModelScope.launch {
            mMovieRepo.getPopular().collect {
                popularMovies = it
            }
        }

    }

}