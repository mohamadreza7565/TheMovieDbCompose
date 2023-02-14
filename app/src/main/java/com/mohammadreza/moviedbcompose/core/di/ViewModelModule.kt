package com.mohammadreza.moviedbcompose.core.di

import com.mohammadreza.moviedbcompose.ui.screens.details.DetailsViewModel
import com.mohammadreza.moviedbcompose.ui.screens.main.MainViewModel
import com.mohammadreza.moviedbcompose.ui.screens.popular.PopularViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Create by Mohammadreza Allahgholi
 *  Site: https://seniorandroid.ir
 */
val viewModelModule = module {
    viewModel { MainViewModel(get()) }
    viewModel { PopularViewModel(get()) }
    viewModel { (id: Int) -> DetailsViewModel(get(),id = id) }
}

