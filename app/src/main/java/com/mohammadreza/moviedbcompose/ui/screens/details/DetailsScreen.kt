package com.mohammadreza.moviedbcompose.ui.screens.details

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.mohammadreza.moviedbcompose.global.ScreenConst
import com.mohammadreza.moviedbcompose.ui.theme.DarkBlue

/**
 * Create by Mohammadreza Allahgholi
 *  Site: https://seniorandroid.ir
 */

fun NavController.openMovieDetails() {
    navigate(ScreenConst.DETAILS_SCREEN)
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun DetailsScreen() {

    Scaffold(
        modifier = Modifier.fillMaxSize().background(DarkBlue),
        content = {
            Text(text = "Hi")
        })

}