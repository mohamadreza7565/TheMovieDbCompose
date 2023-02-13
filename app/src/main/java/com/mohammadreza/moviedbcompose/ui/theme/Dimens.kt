package com.mohammadreza.moviedbcompose.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import com.mohammadreza.moviedbcompose.global.sdp
import com.mohammadreza.moviedbcompose.global.textSsp

/**
 * Create by Mohammadreza Allahgholi
 *  Site: https://seniorandroid.ir
 */


class Dimens {

    companion object {

        /* Start text sizes */
        val text_h1: TextUnit
            @Composable
            get() = 18.textSsp
        val text_h2: TextUnit
            @Composable
            get() = 16.textSsp
        val text_h3: TextUnit
            @Composable
            get() = 14.textSsp
        val text_h4: TextUnit
            @Composable
            get() = 12.textSsp
        val text_h5: TextUnit
            @Composable
            get() = 10.textSsp
        val text_h6: TextUnit
            @Composable
            get() = 8.textSsp
        val text_small: TextUnit
            @Composable
            get() = 6.textSsp
        val text_title: TextUnit
            @Composable
            get() = 28.textSsp
        val text_subtitle: TextUnit
            @Composable
            get() = 8.textSsp
        /* End text sizes */


        /* Start margins */
        val standard_margin_very_small: Dp
            @Composable
            get() = 2.sdp
        val standard_margin_small: Dp
            @Composable
            get() = 4.sdp
        val standard_margin_medium: Dp
            @Composable
            get() = 15.sdp
        val standard_margin_big: Dp
            @Composable
            get() = 30.sdp
        val standard_margin_very_big: Dp
            @Composable
            get() = 55.sdp
        /* End margins */

        /* Start radius */
        val standard_radius_very_small: Dp
            @Composable
            get() = 4.sdp
        val standard_radius_small: Dp
            @Composable
            get() = 8.sdp
        val standard_radius_big: Dp
            @Composable
            get() = 20.sdp
        val standard_radius_very_big: Dp
            @Composable
            get() = 35.sdp
        /* End radius */

        /* Start elevation */
        val standard_elevation_small: Dp
            @Composable
            get() = 2.sdp
        val standard_elevation_default: Dp
            @Composable
            get() = 4.sdp
        /* End radius */


        /* Start heights */
        val toolbar_height: Dp
            @Composable
            get() = 50.sdp
        /* End heights */




    }



}

