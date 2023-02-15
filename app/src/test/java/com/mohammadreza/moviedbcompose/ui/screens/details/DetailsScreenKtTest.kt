package com.mohammadreza.moviedbcompose.ui.screens.details


import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.mohammadreza.moviedbcompose.ui.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Create by Mohammadreza Allahgholi
 * Site: https://seniorandroid.ir
 */

@RunWith(AndroidJUnit4::class)
class DetailsScreenKtTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun informationScreen() {
        composeTestRule.onNodeWithText("language").assertExists()
    }
}
