package com.mohammadreza.moviedbcompose.ui.screens.popular

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.mohammadreza.moviedbcompose.global.sdp
import com.mohammadreza.moviedbcompose.ui.theme.BlackLight
import com.mohammadreza.moviedbcompose.R
import com.mohammadreza.moviedbcompose.global.ScreenConst
import com.mohammadreza.moviedbcompose.global.ScreenRouteConst
import com.mohammadreza.moviedbcompose.ui.screens.details.DetailsScreen
import com.mohammadreza.moviedbcompose.ui.screens.details.openMovieDetails
import com.mohammadreza.moviedbcompose.ui.theme.Dimens
import com.mohammadreza.moviedbcompose.ui.theme.ToolbarColor

/**
 * Create by Mohammadreza Allahgholi
 *  Site: https://seniorandroid.ir
 */

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun PopularListScreen(navController: NavHostController) {

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = { ToolbarView() },
        content = {
            MovieList(mOnItemClickListener = {
                navController.openMovieDetails()
            })
        }
    )

}


@Composable
private fun ToolbarView() {

    val mContext = LocalContext.current

    Row(
        modifier = Modifier
            .background(ToolbarColor)
            .padding(
                start = Dimens.standard_margin_medium,
                end = Dimens.standard_margin_medium,
                top = 0.sdp,
                bottom = 0.sdp
            )
            .fillMaxWidth()
            .height(Dimens.toolbar_height),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {

        Text(
            text = mContext.getString(R.string.popular),
            color = Color.White,
            fontSize = Dimens.text_h3
        )

        AsyncImage(
            modifier = Modifier
                .height(25.sdp)
                .width(25.sdp)
                .clip(CircleShape),
            model = R.drawable.ic_filter_left,
            contentDescription = null,
            contentScale = ContentScale.Crop,
        )

    }
}


@Composable
fun MovieList(
    mOnItemClickListener: () -> Unit
) {

    LazyVerticalGrid(
        userScrollEnabled = false,
        modifier = Modifier
            .background(Color(0xFF2f2c2b))
            .fillMaxSize(),
        columns = GridCells.Fixed(3),
        contentPadding = PaddingValues(Dimens.standard_margin_small),
        content = {

            item {
                PopularMovieItem(mOnItemClickListener)
            }
            item {
                PopularMovieItem(mOnItemClickListener)
            }
            item {
                PopularMovieItem(mOnItemClickListener)
            }
            item {
                PopularMovieItem(mOnItemClickListener)
            }
            item {
                PopularMovieItem(mOnItemClickListener)
            }
        })

}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun PopularMovieItem(mOnItemClickListener: () -> Unit) {

    Card(modifier = Modifier
        .fillMaxWidth()
        .height(180.sdp)
        .padding(Dimens.standard_margin_very_small),
        backgroundColor = BlackLight,
        onClick = {
            mOnItemClickListener.invoke()
        }, content = {
            Column(
                content = {
                    AsyncImage(
                        modifier = Modifier
                            .height(130.sdp)
                            .fillMaxWidth(),
                        model = "https://engineerit93.ir/files/cover.jpg",
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                    )

                    Box(modifier = Modifier
                        .fillMaxSize()
                        .padding(
                            start = Dimens.standard_margin_small,
                            end = Dimens.standard_margin_small
                        ),
                        contentAlignment = Alignment.Center,
                        content = {
                            Text(
                                text = "item 1",
                                textAlign = TextAlign.Center,
                                fontSize = Dimens.text_h5,
                                color = MaterialTheme.colors.background
                            )
                        })
                })
        })
}

@Preview(showBackground = true)
@Composable
fun PopularListScreenPreview() {
    PopularListScreen(rememberNavController())
}
