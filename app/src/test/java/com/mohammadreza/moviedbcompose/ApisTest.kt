package com.mohammadreza.moviedbcompose


import androidx.paging.PagingSource
import com.google.gson.annotations.SerializedName
import com.mohammadreza.moviedbcompose.data.api.MovieApiService
import com.mohammadreza.moviedbcompose.data.model.*
import com.mohammadreza.moviedbcompose.data.repo.MovieRepo
import com.mohammadreza.moviedbcompose.ui.screens.popular.PopularPagingSource
import com.mohammadreza.moviedbcompose.ui.screens.popular.PopularViewModel
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Timeout
import java.util.concurrent.TimeUnit

/**
 * Create by Mohammadreza Allahgholi
 *  Site: https://seniorandroid.ir
 */
class ApisTest {

    private val mMovieApiService = mockk<MovieApiService>(relaxed = true)

    @Test
    @Timeout(value = 60, unit = TimeUnit.SECONDS)
    fun popularApiText() = runTest {

        try {
            val response = mMovieApiService.getPopularMovies(1)
            assertEquals(response.code(), 200)
        } catch (e: Exception) {
            assertThrows(
                Exception::class.java
            ) { throw Exception("Error response") }
        }

    }


    @Test
    @OptIn(ExperimentalCoroutinesApi::class)
    @Timeout(value = 60, unit = TimeUnit.SECONDS)
    fun movieDetailsApiText() = runTest {
        val response = mMovieApiService.getMovieDetails(505642)
        assertEquals(response.code(), 200)

    }



}