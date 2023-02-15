package com.mohammadreza.moviedbcompose.ui.screens.popular

import androidx.compose.foundation.rememberScrollState
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.rememberNavController
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.mohammadreza.moviedbcompose.factory.MovieFactory
import com.mohammadreza.moviedbcompose.ui.screens.details.DetailsScreen
import com.mohammadreza.moviedbcompose.ui.screens.details.MovieDetailsScreen
import com.mohammadreza.moviedbcompose.ui.screens.main.MainApp
import com.mohammadreza.moviedbcompose.ui.theme.White
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Create by Mohammadreza Allahgholi
 * Site: https://seniorandroid.ir
 */

@RunWith(AndroidJUnit4::class)
internal class PopularListScreenKtTest {


    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {
    }

    @Test
    fun ifPopularLoadingIsDisplayed() {

        composeTestRule.setContent {
            PopularListScreen(navController = rememberNavController())
        }
        composeTestRule.onNodeWithTag("POPULAR_LOADING").assertIsDisplayed()
    }


    @Test
    fun ifPopularTitleIsDisplayed() {

        composeTestRule.setContent {
            PopularListScreen(navController = rememberNavController())
        }
        composeTestRule.onNodeWithText("Popular").assertIsDisplayed()
    }


    @Test
    fun ifPopularFilterIsDisplayed() {

        composeTestRule.setContent {
            PopularListScreen(navController = rememberNavController())
        }
        composeTestRule.onNodeWithTag("FILTER_ICON").assertIsDisplayed()
    }


    @Test
    fun ifPopularFilterClick() {

        composeTestRule.setContent {
            MainApp()
        }
        composeTestRule.onNodeWithTag("FILTER_ICON").performClick()
    }

    @Test
    fun ifPopularFilterClickAndDisplayLoadingMovieDetails() {

        composeTestRule.setContent {
            MainApp()
        }
        composeTestRule.onNodeWithTag("POPULAR_LOADING").performClick()
        composeTestRule.onNodeWithTag("FILTER_ICON").performClick()
        composeTestRule.onNodeWithTag("MOVIE_DETAILS_LOADING").performClick()
    }


    @Test
    fun ifMovieDetailsIsDisplayed() {
        composeTestRule.setContent {
            DetailsScreen(
                scrollState = rememberScrollState(),
                model = MovieFactory().createMokMovie(),
                alpha = 0f,
                tint = White.toArgb(),
                navController = rememberNavController()
            )
        }

        composeTestRule.onNodeWithTag("DETAILS_BACK_ICON").assertIsDisplayed()
        composeTestRule.onNodeWithText("Overview").assertIsDisplayed()
        composeTestRule.onNodeWithText("Release date").assertIsDisplayed()
        composeTestRule.onNodeWithText("Language").assertIsDisplayed()
    }


    @Test
    fun ifDetailsLoadingIsDisplayed() {

        composeTestRule.setContent {
            MovieDetailsScreen(id = 1001, navController = rememberNavController())
        }
        composeTestRule.onNodeWithTag("MOVIE_DETAILS_LOADING").assertIsDisplayed()
    }

}