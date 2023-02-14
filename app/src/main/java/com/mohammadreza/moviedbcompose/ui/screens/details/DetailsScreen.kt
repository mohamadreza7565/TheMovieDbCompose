package com.mohammadreza.moviedbcompose.ui.screens.details

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.util.Log
import android.widget.ImageButton
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.keyframes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.graphics.ColorUtils
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.mohammadreza.moviedbcompose.R
import com.mohammadreza.moviedbcompose.global.ScreenConst
import com.mohammadreza.moviedbcompose.global.ScreenController
import com.mohammadreza.moviedbcompose.global.ScreenController.removeStatusBar
import com.mohammadreza.moviedbcompose.global.ScreenController.showStatusBar
import com.mohammadreza.moviedbcompose.global.sdp
import com.mohammadreza.moviedbcompose.ui.theme.*
import org.koin.androidx.compose.getViewModel
import org.koin.androidx.compose.inject
import org.koin.java.KoinJavaComponent

/**
 * Create by Mohammadreza Allahgholi
 *  Site: https://seniorandroid.ir
 */

fun NavController.openMovieDetails(id: Int) {

    currentBackStackEntry?.savedStateHandle?.apply {
        set(
            "id",
            id
        )
    }

    navigate(route = ScreenConst.DETAILS_SCREEN)
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun DetailsScreen(
    id: Int,
    mLoadingStateListener: (Boolean) -> Unit,
    navController: NavHostController,
    mViewModel: DetailsViewModel = getViewModel(),
) {

    val systemUiController: SystemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(color = Color.Transparent)
    val activity = LocalContext.current as Activity
    activity.removeStatusBar()

    val scrollState = rememberScrollState()

    val alpha by animateFloatAsState(
        targetValue = scrollState.value / 250f,
        animationSpec = keyframes { durationMillis = 1 })

    val tint by animateIntAsState(
        targetValue = getTintWithScroll(alpha),
        animationSpec = keyframes { durationMillis = 1 })


    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBlue),
        content = {

            Box(modifier = Modifier.fillMaxWidth(), content = {

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(scrollState),
                    content = {
                        HeaderScreen()
                        DividerScreen()
                        DetailsRateScreen()
                        DividerScreen()
                        OverViewScreen()

                    })


                ToolbarScreen(alpha, tint, navController)

            })


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

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                content = {

                    IconButton(
                        modifier = Modifier
                            .height(36.dp)
                            .width(36.dp)
                            .clip(CircleShape),
                        onClick = {
                            navController.popBackStack()
                        }, content = {
                            Icon(
                                painter = rememberVectorPainter(image = Icons.Default.ArrowBack),
                                contentDescription = "",
                                tint = Color(tint),
                            )
                        })

                    Row(
                        modifier = Modifier
                            .height(Dimens.toolbar_height),
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically
                    ) {


                        IconButton(
                            modifier = Modifier
                                .height(36.dp)
                                .width(36.dp)
                                .clip(CircleShape),
                            onClick = {

                            }, content = {
                                Icon(
                                    painter = rememberVectorPainter(image = Icons.Default.Share),
                                    contentDescription = "",
                                    tint = Color(tint),
                                )
                            })

                        Spacer(modifier = Modifier.width(Dimens.standard_margin_small))

                        IconButton(
                            modifier = Modifier
                                .height(36.dp)
                                .width(36.dp)
                                .clip(CircleShape),
                            onClick = {

                            }, content = {
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
private fun OverViewScreen() {

    val mContext = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(Dimens.standard_margin_medium)
    ) {

        Spacer(modifier = Modifier.height(Dimens.standard_margin_big))

        Text(text = mContext.getString(R.string.overview), fontFamily = FontFamily(Font(boldFont)))

        Spacer(modifier = Modifier.height(Dimens.standard_margin_medium))

        Text(text = mContext.getString(R.string.lorem))

        Spacer(modifier = Modifier.height(Dimens.standard_margin_big))

    }

}

@Composable
fun DetailsRateScreen() {

    Row(modifier = Modifier.fillMaxWidth(),
        content = {

            Column(
                modifier = Modifier
                    .aspectRatio(1.5f)
                    .padding(start = Dimens.standard_margin_medium)
                    .weight(1f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {

                    Text(text = "8.2")

                    Spacer(modifier = Modifier.width(Dimens.standard_margin_very_small))

                    AsyncImage(
                        modifier = Modifier
                            .height(25.sdp)
                            .width(25.sdp),
                        model = R.drawable.ic_filter_left,
                        colorFilter = ColorFilter.tint(Black),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                    )

                }

                Text(text = "10192 Votes", fontSize = Dimens.text_h5, color = Gray)

            }

            Column(
                modifier = Modifier
                    .aspectRatio(1.5f)
                    .padding(start = Dimens.standard_margin_medium)
                    .weight(1f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {


                    AsyncImage(
                        modifier = Modifier
                            .height(25.sdp)
                            .width(25.sdp),
                        model = R.drawable.ic_filter_left,
                        colorFilter = ColorFilter.tint(Black),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                    )


                    Spacer(modifier = Modifier.width(Dimens.standard_margin_very_small))

                    Text(text = "8.2")

                }

                Text(text = "10192 Votes", fontSize = Dimens.text_h5, color = Gray)

            }

            Column(
                modifier = Modifier
                    .aspectRatio(1.5f)
                    .padding(
                        end = Dimens.standard_margin_medium,
                        start = Dimens.standard_margin_medium
                    )
                    .weight(1f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {


                    AsyncImage(
                        modifier = Modifier
                            .height(25.sdp)
                            .width(25.sdp),
                        model = R.drawable.ic_filter_left,
                        colorFilter = ColorFilter.tint(Black),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                    )

                    Spacer(modifier = Modifier.width(Dimens.standard_margin_very_small))

                    Text(text = "8.2")


                }

                Text(text = "10192 Votes", fontSize = Dimens.text_h5, color = Gray)

            }


        })

}


@Composable
private fun HeaderScreen() {

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
            model = "https://engineerit93.ir/files/cover_2.jpg",
            contentDescription = null,
            contentScale = ContentScale.Crop,
        )


        val dividerBottomMargin = Dimens.standard_margin_big

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(divider) {
                    bottom.linkTo(cover.bottom, margin = dividerBottomMargin)
                }
        )


        val titleAndTagsMargin = Dimens.standard_margin_medium

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(titleAndTags) {
                    top.linkTo(cover.bottom, margin = titleAndTagsMargin)
                    start.linkTo(poster.end, margin = titleAndTagsMargin)
                }
        ) {
            Text(text = "Title", fontFamily = FontFamily(Font(boldFont)))

            Row {
                Text(text = "Tags")
                Text(text = "Tags")
                Text(text = "Tags")
            }

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
                model = "https://engineerit93.ir/files/cover.jpg",
                contentDescription = null,
                contentScale = ContentScale.Crop,
            )
        }

    }

}

@Preview(showBackground = true)
@Composable
fun DetailsScreenPreview() {
    ToolbarScreen(1f, Color.Black.toArgb(), rememberNavController())
}

fun getTintWithScroll(alpha: Float): Int {

    val mContext: Context by KoinJavaComponent.inject(Context::class.java)

    if (alpha < 1) {
        val resultColor = ColorUtils.blendARGB(
            ContextCompat.getColor(
                mContext,
                R.color.white
            ),

            ContextCompat.getColor(
                mContext,
                R.color.black
            ),

            alpha
        )

        return resultColor
    } else {
        return Color.Black.toArgb()
    }


}
