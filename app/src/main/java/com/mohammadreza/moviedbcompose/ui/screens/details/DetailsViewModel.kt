package com.mohammadreza.moviedbcompose.ui.screens.details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.mohammadreza.moviedbcompose.core.base.BaseApiDataState
import com.mohammadreza.moviedbcompose.core.base.BaseViewModel
import com.mohammadreza.moviedbcompose.data.model.MovieModel
import com.mohammadreza.moviedbcompose.data.repo.MovieRepo
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

/**
 * Create by Mohammadreza Allahgholi
 *  Site: https://seniorandroid.ir
 */
class DetailsViewModel(private val mMovieRepo: MovieRepo, private val id: Int) : BaseViewModel() {

    var movieDetails by mutableStateOf<BaseApiDataState<MovieModel>>(BaseApiDataState.Loading)

    init {
        getMovieDetails(id)
    }

    fun getMovieDetails(id: Int) {
        viewModelScope.launch {
            mMovieRepo.getMovieDetails(id).collect {
                movieDetails = it
            }
        }
    }


    fun doLike(id: Int) {
        viewModelScope.launch {
            mMovieRepo.doLike(id)
        }
    }

    suspend fun isLiked(id: Int) = flow { emit(mMovieRepo.isLiked(id)) }

}