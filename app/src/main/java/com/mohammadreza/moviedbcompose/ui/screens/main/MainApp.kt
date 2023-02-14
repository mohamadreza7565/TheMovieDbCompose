package com.mohammadreza.moviedbcompose.ui.screens.main

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntOffset
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mohammadreza.moviedbcompose.global.ScreenConst
import com.mohammadreza.moviedbcompose.ui.components.LoadingScreen
import com.mohammadreza.moviedbcompose.ui.screens.details.DetailsScreen
import com.mohammadreza.moviedbcompose.ui.screens.popular.PopularListScreen

/**
 * Create by Mohammadreza Allahgholi
 *  Site: https://seniorandroid.ir
 */
@Composable
fun MainApp() {

    val navController = rememberNavController()
    var loadingState by remember { mutableStateOf(false) }
    var routeState by remember { mutableStateOf(ScreenConst.POPULAR_SCREEN) }

    Scaffold(
        content = { innerPadding ->
            Navigation(
                navController = navController,
                mLoadingStateListener = {
                    routeState = navController.currentDestination?.route!!
                    loadingState = it
                }
            )

            LoadingScreen(
                navController = navController,
                route = routeState,
                modifier = Modifier.padding(innerPadding),
                visible = loadingState
            )

        }
    )

}


@Composable
fun Navigation(
    navController: NavHostController,
    mLoadingStateListener: (Boolean) -> Unit
) {


    NavHost(
        navController = navController,
        startDestination = ScreenConst.POPULAR_SCREEN,
    ) {

        composable(
            route = ScreenConst.POPULAR_SCREEN,
        ) { navBackStackEntry ->


            PopularListScreen(
                navController = navController,
                mLoadingStateListener = mLoadingStateListener
            )

        }

        composable(
            route = ScreenConst.DETAILS_SCREEN
        ) { navBackStackEntry ->
            val id = navController.previousBackStackEntry?.savedStateHandle?.get<Int>("id")
            DetailsScreen(
                id = id ?: 0,
                mLoadingStateListener = mLoadingStateListener,
                navController = navController
            )
        }

    }


}