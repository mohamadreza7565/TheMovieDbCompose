package com.mohammadreza.moviedbcompose.ui.screens.details

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.keyframes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.Timer
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.core.content.ContextCompat
import androidx.core.graphics.ColorUtils
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.mohammadreza.moviedbcompose.BuildConfig
import com.mohammadreza.moviedbcompose.R
import com.mohammadreza.moviedbcompose.core.base.BaseApiDataState
import com.mohammadreza.moviedbcompose.data.model.Genre
import com.mohammadreza.moviedbcompose.data.model.MovieModel
import com.mohammadreza.moviedbcompose.global.ScreenConst
import com.mohammadreza.moviedbcompose.global.ScreenController.removeStatusBar
import com.mohammadreza.moviedbcompose.global.sdp
import com.mohammadreza.moviedbcompose.ui.components.FillLoadingScreen
import com.mohammadreza.moviedbcompose.ui.theme.*
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf
import org.koin.java.KoinJavaComponent

/**
 * Create by Mohammadreza Allahgholi
 *  Site: https://seniorandroid.ir
 */

fun NavController.openMovieDetails(id: Int) {

    currentBackStackEntry?.savedStateHandle?.apply {
        set(
            "id", id
        )
    }

    navigate(route = ScreenConst.DETAILS_SCREEN)
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun DetailsScreen(
    id: Int,
    navController: NavHostController,
    mViewModel: DetailsViewModel = getViewModel(parameters = { parametersOf(id) }),
) {

    val systemUiController: SystemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(color = Color.Transparent)
    val activity = LocalContext.current as Activity
    activity.removeStatusBar()

    val scrollState = rememberScrollState()

    val alpha by animateFloatAsState(targetValue = scrollState.value / 250f,
        animationSpec = keyframes { durationMillis = 1 })

    val tint by animateIntAsState(targetValue = getTintWithScroll(alpha),
        animationSpec = keyframes { durationMillis = 1 })


    Scaffold(modifier = Modifier
        .fillMaxSize()
        .background(DarkBlue), content = {

        FillLoadingScreen(
            modifier = Modifier.fillMaxSize(),
            visible = mViewModel.movieDetails is BaseApiDataState.Loading
        )

        mViewModel.movieDetails.let {
            when (it) {
                is BaseApiDataState.Success -> {

                    it.data?.let {
                        Box(modifier = Modifier.fillMaxWidth(), content = {

                            Column(modifier = Modifier
                                .fillMaxSize()
                                .verticalScroll(scrollState),
                                content = {
                                    HeaderScreen(it)
                                    DividerScreen()
                                    DetailsRateScreen(it)
                                    DividerScreen()
                                    OverViewScreen(it.overview)

                                })


                            ToolbarScreen(alpha, tint, navController)

                        })
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


    })

}

@Composable
private fun ToolbarScreen(alpha: Float, tint: Int, navController: NavHostController) {

    val paddingValues = WindowInsets.systemBars.asPaddingValues()
    Column(modifier = Modifier.fillMaxWidth()) {

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(paddingValues.calculateTopPadding())
                .alpha(alpha)
                .background(White)
        )

        Box(modifier = Modifier.fillMaxWidth()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(Dimens.toolbar_height)
                    .alpha(alpha)
                    .background(White)
            )

            Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                content = {

                    IconButton(modifier = Modifier
                        .height(36.dp)
                        .width(36.dp)
                        .clip(CircleShape),
                        onClick = {
                            navController.popBackStack()
                        },
                        content = {
                            Icon(
                                painter = rememberVectorPainter(image = Icons.Default.ArrowBack),
                                contentDescription = "",
                                tint = Color(tint),
                            )
                        })

                    Row(
                        modifier = Modifier.height(Dimens.toolbar_height),
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically
                    ) {


                        IconButton(modifier = Modifier
                            .height(36.dp)
                            .width(36.dp)
                            .clip(CircleShape),
                            onClick = {

                            },
                            content = {
                                Icon(
                                    painter = rememberVectorPainter(image = Icons.Default.Share),
                                    contentDescription = "",
                                    tint = Color(tint),
                                )
                            })

                        Spacer(modifier = Modifier.width(Dimens.standard_margin_small))

                        IconButton(modifier = Modifier
                            .height(36.dp)
                            .width(36.dp)
                            .clip(CircleShape),
                            onClick = {

                            },
                            content = {
                                Icon(
                                    painter = rememberVectorPainter(image = Icons.Default.FavoriteBorder),
                                    contentDescription = "",
                                    tint = Color(tint),
                                )
                            })

                        Spacer(modifier = Modifier.width(Dimens.standard_margin_medium))
                    }

                })
        }
    }

}

@Composable
private fun DividerScreen() {
    Spacer(
        modifier = Modifier
            .fillMaxWidth()
            .height(1.sdp)
            .background(GrayLight)
    )
}

@Composable
private fun OverViewScreen(overview: String) {

    val mContext = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(Dimens.standard_margin_medium)
    ) {

        Spacer(modifier = Modifier.height(Dimens.standard_margin_big))

        Text(text = mContext.getString(R.string.overview), fontFamily = FontFamily(Font(boldFont)))

        Spacer(modifier = Modifier.height(Dimens.standard_margin_medium))

        Text(text = overview)

        Spacer(modifier = Modifier.height(Dimens.standard_margin_big))

    }

}

@Composable
fun DetailsRateScreen(model: MovieModel) {

    val mContext: Context by KoinJavaComponent.inject(Context::class.java)

    Row(modifier = Modifier.fillMaxWidth(), content = {

        Column(
            modifier = Modifier
                .aspectRatio(1.5f)
                .padding(start = Dimens.standard_margin_small)
                .weight(1f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {

                Text(
                    text = "${model.voteAverage}",
                    fontSize = Dimens.text_h5,
                    fontFamily = FontFamily(Font(boldFont))
                )

                Spacer(modifier = Modifier.width(Dimens.standard_margin_very_small))

                Box(modifier = Modifier
                    .height(15.sdp)
                    .width(15.sdp), content = {
                    Icon(
                        painter = rememberVectorPainter(image = Icons.Default.Star),
                        contentDescription = "",
                        tint = Black,
                    )
                })

            }

            Text(
                modifier = Modifier.padding(top = Dimens.standard_margin_very_small),
                text = "${model.voteCount} ${mContext.getString(R.string.votes)}",
                fontSize = Dimens.text_h5,
                color = Gray
            )

        }

        Column(
            modifier = Modifier
                .aspectRatio(1.5f)
                .weight(1f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {


                Box(modifier = Modifier
                    .height(15.sdp)
                    .width(15.sdp), content = {
                    Icon(
                        painter = rememberVectorPainter(image = Icons.Default.Language),
                        contentDescription = "",
                        tint = Black,
                    )
                })

                Spacer(modifier = Modifier.width(Dimens.standard_margin_very_small))

                Text(
                    text = mContext.getString(R.string.language),
                    fontSize = Dimens.text_h5,
                    fontFamily = FontFamily(Font(boldFont))
                )

            }

            Text(
                modifier = Modifier.padding(top = Dimens.standard_margin_very_small),
                text = model.originalLanguage,
                fontSize = Dimens.text_h5,
                color = Gray
            )

        }

        Column(
            modifier = Modifier
                .aspectRatio(1.5f)
                .padding(
                    end = Dimens.standard_margin_small,
                )
                .weight(1f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {


                Box(modifier = Modifier
                    .height(15.sdp)
                    .width(15.sdp), content = {
                    Icon(
                        painter = rememberVectorPainter(image = Icons.Outlined.Timer),
                        contentDescription = "",
                        tint = Black,
                    )
                })

                Spacer(modifier = Modifier.width(Dimens.standard_margin_very_small))

                Text(
                    text = model.releaseDate,
                    fontSize = Dimens.text_h5,
                    fontFamily = FontFamily(Font(boldFont))
                )


            }


            Text(
                modifier = Modifier.padding(top = Dimens.standard_margin_very_small),
                text = mContext.getString(R.string.release_date),
                fontSize = Dimens.text_h5,
                color = Gray
            )

        }


    })

}


@Composable
private fun HeaderScreen(model: MovieModel) {

    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        val (cover, divider, titleAndTags, poster) = createRefs()


        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .defaultMinSize(minHeight = 250.dp)
                .constrainAs(cover) {
                    top.linkTo(parent.top)
                },
            model = BuildConfig.BASE_IMAGE_URL + model.backdropPath,
            contentDescription = null,
            contentScale = ContentScale.Crop,
        )


        val dividerBottomMargin = Dimens.standard_margin_big

        Box(modifier = Modifier
            .fillMaxWidth()
            .constrainAs(divider) {
                bottom.linkTo(cover.bottom, margin = dividerBottomMargin)
            })


        val titleAndTagsMargin = Dimens.standard_margin_medium

        Column(modifier = Modifier.constrainAs(titleAndTags) {
            top.linkTo(cover.bottom, margin = titleAndTagsMargin)
            start.linkTo(poster.end, margin = titleAndTagsMargin)
            end.linkTo(parent.end)
            width = Dimension.fillToConstraints
        }) {

            Text(text = model.originalTitle, fontFamily = FontFamily(Font(boldFont)))

            GenreListScreen(genre = model.genres)

        }

        val posterMargin = Dimens.standard_margin_medium

        Card(modifier = Modifier
            .height(160.sdp)
            .width(100.sdp)
            .padding(bottom = Dimens.standard_margin_medium)
            .defaultMinSize(minHeight = 250.dp)
            .constrainAs(poster) {
                top.linkTo(divider.bottom)
                start.linkTo(parent.start, margin = posterMargin)
            }) {
            AsyncImage(
                modifier = Modifier.fillMaxSize(),
                model = BuildConfig.BASE_IMAGE_URL + model.posterPath,
                contentDescription = null,
                contentScale = ContentScale.Crop,
            )
        }

    }

}

@Preview(showBackground = true)
@Composable
fun DetailsScreenPreview() {
    GenreItemScreen(genre = Genre(id = 1, name = "Genre"))
}

fun getTintWithScroll(alpha: Float): Int {

    val mContext: Context by KoinJavaComponent.inject(Context::class.java)

    if (alpha < 1) {
        val resultColor = ColorUtils.blendARGB(
            ContextCompat.getColor(
                mContext, R.color.white
            ),

            ContextCompat.getColor(
                mContext, R.color.black
            ),

            alpha
        )

        return resultColor
    } else {
        return Color.Black.toArgb()
    }


}

@Composable
fun GenreListScreen(
    genre: MutableList<Genre>,
) {
    FlowRow {
        genre.forEach {
            GenreItemScreen(it)
        }
    }

}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun GenreItemScreen(genre: Genre) {

    Card(modifier = Modifier
        .height(30.sdp)
        .padding(Dimens.standard_margin_very_small),
        shape = RoundedCornerShape(15.dp),
        border = BorderStroke(1.sdp, Gray),
        backgroundColor = White,
        onClick = {}) {

        Box(
            modifier = Modifier.padding(Dimens.standard_margin_small),
            contentAlignment = Alignment.Center
        ) {
            Text(
                textAlign = TextAlign.Center,
                text = genre.name,
            )
        }

    }

}
