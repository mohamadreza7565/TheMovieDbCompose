package com.mohammadreza.moviedbcompose.ui.screens.main

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mohammadreza.moviedbcompose.global.ScreenConst
import com.mohammadreza.moviedbcompose.ui.components.FillLoadingScreen
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
    var routeState by remember { mutableStateOf(ScreenConst.POPULAR_SCREEN) }

    Scaffold(
        content = {
            Navigation(
                navController = navController
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
            route = ScreenConst.POPULAR_SCREEN,
        ) { navBackStackEntry ->

            PopularListScreen(
                navController = navController,
            )

        }

        composable(
            route = ScreenConst.DETAILS_SCREEN
        ) { navBackStackEntry ->
            val id = navController.previousBackStackEntry?.savedStateHandle?.get<Int>("id")
            DetailsScreen(
                id = id ?: 0,
                navController = navController
            )
        }

    }


}