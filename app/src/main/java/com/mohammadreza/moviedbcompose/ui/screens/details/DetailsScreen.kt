package com.mohammadreza.moviedbcompose.ui.screens.details

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.mohammadreza.moviedbcompose.global.ScreenConst
import com.mohammadreza.moviedbcompose.ui.theme.DarkBlue

/**
 * Create by Mohammadreza Allahgholi
 *  Site: https://seniorandroid.ir
 */

fun NavController.openMovieDetails(id:Int) {

    currentBackStackEntry?.savedStateHandle?.apply {
        set(
            "id",
            id
        )
    }

    navigate(route = ScreenConst.DETAILS_SCREEN){


    // reselecting the same item
    launchSingleTop = true
    // Restore state when reselecting a previously selected item
    restoreState = true
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun DetailsScreen(id:Int) {

    Scaffold(
        modifier = Modifier.fillMaxSize().background(DarkBlue),
        content = {
            Text(text = "Hi - $id")
        })

}