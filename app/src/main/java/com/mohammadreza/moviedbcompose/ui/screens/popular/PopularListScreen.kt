package com.mohammadreza.moviedbcompose.ui.screens.popular

import android.annotation.SuppressLint
import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.mohammadreza.moviedbcompose.BuildConfig
import com.mohammadreza.moviedbcompose.global.sdp
import com.mohammadreza.moviedbcompose.R
import com.mohammadreza.moviedbcompose.core.base.BaseApiDataState
import com.mohammadreza.moviedbcompose.data.model.MovieModel
import com.mohammadreza.moviedbcompose.global.ScreenController.showStatusBar
import com.mohammadreza.moviedbcompose.ui.screens.details.openMovieDetails
import com.mohammadreza.moviedbcompose.ui.theme.*
import org.koin.androidx.compose.getViewModel

/**
 * Create by Mohammadreza Allahgholi
 *  Site: https://seniorandroid.ir
 */

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun PopularListScreen(
    navController: NavHostController,
    mViewModel: PopularViewModel = getViewModel(),
    mLoadingStateListener: (Boolean) -> Unit
) {

    val systemUiController: SystemUiController = rememberSystemUiController()

    systemUiController.setStatusBarColor(color = DarkBlue)
    val activity = LocalContext.current as Activity
    activity.showStatusBar(color = DarkBlue.toArgb())

    mLoadingStateListener.invoke(mViewModel.popularMovies is BaseApiDataState.Loading)

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = { ToolbarView() },
        content = { padding ->

            mViewModel.popularMovies.let {
                when (it) {

                    is BaseApiDataState.Success -> {
                        it.data?.movies?.let {
                            if (it.isEmpty()) {
                                // show empty
                            } else {
                                MovieList(list = it, mOnItemClickListener = { id ->
                                    navController.openMovieDetails(id)
                                })
                            }
                        } ?: run {
                            // show error
                        }
                    }

                    is BaseApiDataState.Error -> {
                        // show error
                    }

                    else -> {}

                }
            }


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

        IconButton(
            modifier = Modifier
                .height(36.dp)
                .width(36.dp)
                .clip(CircleShape),
            onClick = {

            })
        {
            Icon(
                painter = painterResource(R.drawable.ic_filter_left),
                contentDescription = "",
                tint = White,
            )
        }

    }
}


@Composable
fun MovieList(
    list: ArrayList<MovieModel>,
    mOnItemClickListener: (id: Int) -> Unit
) {

    LazyVerticalGrid(
        modifier = Modifier
            .background(Color(0xFF2f2c2b))
            .fillMaxSize(),
        columns = GridCells.Fixed(3),
        contentPadding = PaddingValues(Dimens.standard_margin_small),
        content = {

            items(list) {
                PopularMovieItem(it, mOnItemClickListener)
            }

        })

}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun PopularMovieItem(item: MovieModel, mOnItemClickListener: (id: Int) -> Unit) {

    Card(modifier = Modifier
        .fillMaxWidth()
        .height(180.sdp)
        .padding(Dimens.standard_margin_very_small),
        backgroundColor = BlackLight,
        onClick = {
            mOnItemClickListener.invoke(item.id)
        }, content = {
            Column(
                content = {
                    AsyncImage(
                        modifier = Modifier
                            .height(130.sdp)
                            .fillMaxWidth(),
                        model = BuildConfig.BASE_IMAGE_URL + item.posterPath,
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
                                text = item.originalTitle,
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
    ToolbarView()
}
