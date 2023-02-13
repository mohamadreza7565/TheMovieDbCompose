package com.mohammadreza.moviedbcompose.ui.screens.popular

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.mohammadreza.moviedbcompose.global.sdp
import com.mohammadreza.moviedbcompose.ui.theme.BlackLight
import com.mohammadreza.moviedbcompose.R

/**
 * Create by Mohammadreza Allahgholi
 *  Site: https://seniorandroid.ir
 */

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun PopularListScreen() {

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = { ToolbarView() },
        content = {
            MovieList()
        }
    )

}


@Composable
private fun ToolbarView() {

    val mContext = LocalContext.current

    Row(
        modifier = Modifier
            .background(Color.Black)
            .padding(start = 20.sdp, end = 20.sdp, top = 0.sdp, bottom = 0.sdp)
            .fillMaxWidth()
            .height(55.sdp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {

        Text(text = mContext.getString(R.string.popular), style = TextStyle(color = Color.White))

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
fun MovieList() {

    LazyVerticalGrid(
        userScrollEnabled = false,
        modifier = Modifier
            .background(Color(0xFF2f2c2b))
            .fillMaxSize(),
        columns = GridCells.Fixed(3),
        contentPadding = PaddingValues(4.dp),
        content = {
            item {
                PopularMovieItem()
            }
            item {
                PopularMovieItem()
            }
            item {
                PopularMovieItem()
            }
            item {
                PopularMovieItem()
            }
            item {
                PopularMovieItem()
            }
        })

}

@Composable
private fun PopularMovieItem() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.sdp)
            .padding(4.sdp)
            .background(BlackLight),
        content = {

            AsyncImage(
                modifier = Modifier
                    .height(110.sdp)
                    .fillMaxWidth()
                    .clip(CircleShape),
                model = "https://image.tmdb.org/t/p/w500/sv1xJUazXeYqALzczSZ3O6nkH75.jpg",
                contentDescription = null,
                contentScale = ContentScale.Crop,
            )

            Box(modifier = Modifier
                .fillMaxSize()
                .padding(start = 4.sdp, end = 4.sdp),
                contentAlignment = Alignment.Center,
                content = {
                    Text(
                        text = "item 1",
                        textAlign = TextAlign.Center,
                        style = TextStyle(color = MaterialTheme.colors.background)
                    )
                })
        })
}

@Preview(showBackground = true)
@Composable
fun PopularListScreenPreview() {
    PopularListScreen()
}