package com.mohammadreza.moviedbcompose.ui.screens.main

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mohammadreza.moviedbcompose.global.ScreenConst
import com.mohammadreza.moviedbcompose.global.ScreenRouteConst
import com.mohammadreza.moviedbcompose.ui.screens.details.DetailsScreen
import com.mohammadreza.moviedbcompose.ui.screens.popular.PopularListScreen

/**
 * Create by Mohammadreza Allahgholi
 *  Site: https://seniorandroid.ir
 */

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainApp() {

    val navController = rememberNavController()

    Scaffold(
        content = {
            Navigation(
                navController = navController,
            )
        }
    )

}

@Composable
fun Navigation(
    navController: NavHostController
) {


    NavHost(
        navController = navController,
        startDestination = ScreenConst.POPULAR_SCREEN,
    ) {

        composable(
            route = ScreenRouteConst.POPULAR_MOVIES
        ) { navBackStackEntry ->

            PopularListScreen(navController)

        }

        composable(
            route = ScreenRouteConst.DETAILS_MOVIE
        ) { navBackStackEntry ->

            DetailsScreen()

        }

    }
}