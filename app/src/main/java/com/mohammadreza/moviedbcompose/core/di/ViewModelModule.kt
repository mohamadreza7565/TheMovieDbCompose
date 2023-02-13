package com.mohammadreza.moviedbcompose.core.di

import com.mohammadreza.moviedbcompose.ui.screens.popular.PopularViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Create by Mohammadreza Allahgholi
 *  Site: https://seniorandroid.ir
 */
val viewModelModule = module {
    viewModel { PopularViewModel(get())}
}

