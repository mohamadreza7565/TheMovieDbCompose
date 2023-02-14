package com.mohammadreza.moviedbcompose.ui.screens.details

import com.mohammadreza.moviedbcompose.core.base.BaseViewModel
import com.mohammadreza.moviedbcompose.data.repo.MovieRepo

/**
 * Create by Mohammadreza Allahgholi
 *  Site: https://seniorandroid.ir
 */
class DetailsViewModel(private val mMovieRepo: MovieRepo) : BaseViewModel() {

    fun getMovieDetails(id: Int) = mMovieRepo.getMovieDetails(id)


}