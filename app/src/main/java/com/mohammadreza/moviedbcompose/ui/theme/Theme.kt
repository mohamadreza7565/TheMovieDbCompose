package com.mohammadreza.moviedbcompose.ui.theme


import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val DarkColorPalette = darkColors(
    primary = Black,
    onPrimary = White,
    primaryVariant = Black,
    secondary = White,
    onSecondary = Black,
    background = White,
    onBackground = Black
)



@Composable
fun MovieDbComposeTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {

    val colors = DarkColorPalette

    val systemUiController = rememberSystemUiController()
    systemUiController.setSystemBarsColor(
        color = DarkBlue
    )

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}