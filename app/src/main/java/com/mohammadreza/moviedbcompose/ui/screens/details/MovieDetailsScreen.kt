package com.mohammadreza.moviedbcompose.ui.screens.details

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.keyframes
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.Timer
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
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
fun MovieDetailsScreen(
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
            modifier = Modifier
                .fillMaxSize()
                .testTag("MOVIE_DETAILS_LOADING"),
            visible = mViewModel.movieDetails is BaseApiDataState.Loading
        )

        mViewModel.movieDetails.let {
            when (it) {
                is BaseApiDataState.Success -> {

                    it.data?.let {
                        DetailsScreen(scrollState, it, alpha, tint, navController)
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
fun DetailsScreen(
    scrollState: ScrollState,
    model: MovieModel,
    alpha: Float,
    tint: Int,
    navController: NavHostController
) {
    Box(modifier = Modifier.fillMaxWidth(), content = {

        Column(modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState),
            content = {
                HeaderScreen(model)
                DividerScreen()
                InformationScreen(model)
                DividerScreen()
                OverViewScreen(model.overview)
            })


        ToolbarScreen(alpha, model, tint, navController)

    })
}

@Composable
fun ToolbarScreen(
    alpha: Float,
    model: MovieModel,
    tint: Int,
    navController: NavHostController,
    mViewModel: DetailsViewModel = getViewModel()
) {

    var likedState = remember {
        mutableStateOf<Boolean?>(null)
    }

    LaunchedEffect(Unit) {
        mViewModel.isLiked(model.id).collect {
            likedState.value = it
        }
    }

    var likeIcon = if (likedState.value == true) Icons.Default.Favorite else Icons.Default.FavoriteBorder

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
                        .testTag("DETAILS_BACK_ICON")
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
                                mViewModel.doLike(id = model.id)
                                likedState.value = !(likedState.value?:false)
                            },
                            content = {
                                Icon(
                                    painter = rememberVectorPainter(image = likeIcon),
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
fun DividerScreen() {
    Spacer(
        modifier = Modifier
            .fillMaxWidth()
            .height(1.sdp)
            .background(GrayLight)
    )
}

@Composable
fun OverViewScreen(overview: String) {

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
fun InformationScreen(model: MovieModel) {

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
            InfoWithEndIconTitle(
                title = "${model.voteAverage}",
                details = "${model.voteCount} ${mContext.getString(R.string.votes)}",
                icon = Icons.Default.Star
            )
        }

        Column(
            modifier = Modifier
                .aspectRatio(1.5f)
                .weight(1f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            InfoWithStartIconTitle(
                title = mContext.getString(R.string.language),
                details = model.originalLanguage,
                icon = Icons.Default.Language
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
            InfoWithStartIconTitle(
                title = mContext.getString(R.string.release_date),
                details = model.releaseDate,
                icon = Icons.Outlined.Timer
            )
        }

    })

}

@Composable
fun InfoWithStartIconTitle(
    title: String,
    details: String,
    icon: ImageVector
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {


        Box(modifier = Modifier
            .height(15.sdp)
            .width(15.sdp), content = {
            Icon(
                painter = rememberVectorPainter(image = icon),
                contentDescription = "",
                tint = Black,
            )
        })

        Spacer(modifier = Modifier.width(Dimens.standard_margin_very_small))

        Text(
            text = title,
            fontSize = Dimens.text_h5,
            fontFamily = FontFamily(Font(boldFont))
        )

    }

    Text(
        modifier = Modifier.padding(top = Dimens.standard_margin_very_small),
        text = details,
        fontSize = Dimens.text_h5,
        color = Gray
    )
}

@Composable
fun InfoWithEndIconTitle(
    title: String,
    details: String,
    icon: ImageVector
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {

        Text(
            text = title,
            fontSize = Dimens.text_h5,
            fontFamily = FontFamily(Font(boldFont))
        )

        Spacer(modifier = Modifier.width(Dimens.standard_margin_very_small))

        Box(modifier = Modifier
            .height(15.sdp)
            .width(15.sdp), content = {
            Icon(
                painter = rememberVectorPainter(image = icon),
                contentDescription = "",
                tint = Black,
            )
        })

    }

    Text(
        modifier = Modifier.padding(top = Dimens.standard_margin_very_small),
        text = details,
        fontSize = Dimens.text_h5,
        color = Gray
    )
}


@Composable
fun HeaderScreen(model: MovieModel) {

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

            Text(
                modifier = Modifier.padding(end = Dimens.standard_margin_small),
                text = model.originalTitle,
                fontFamily = FontFamily(Font(boldFont))
            )

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

        return ColorUtils.blendARGB(
            ContextCompat.getColor(
                mContext, R.color.white
            ),

            ContextCompat.getColor(
                mContext, R.color.black
            ),

            alpha
        )
    } else {
        return Color.Black.toArgb()
    }


}

@Composable
fun GenreListScreen(
    genre: MutableList<Genre>,
) {
    FlowRow(
        modifier = Modifier.padding(
            end = Dimens.standard_margin_small,
            top = Dimens.standard_margin_small
        )
    ) {
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
