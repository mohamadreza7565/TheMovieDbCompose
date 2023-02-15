package com.mohammadreza.moviedbcompose.unittest


import com.mohammadreza.moviedbcompose.core.base.BaseApiDataState
import com.mohammadreza.moviedbcompose.core.base.BaseDataSource
import com.mohammadreza.moviedbcompose.data.api.MovieApiService
import io.mockk.mockk
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Timeout
import java.util.concurrent.TimeUnit

/**
 * Create by Mohammadreza Allahgholi
 *  Site: https://seniorandroid.ir
 */


class BaseApiSourceTest : BaseDataSource() {

    private val mMovieApiService = mockk<MovieApiService>(relaxed = true)

    @Test
    @Timeout(value = 60, unit = TimeUnit.SECONDS)
    fun callApiSuccess() = runTest {

        val isSuccess = callApi {
            mMovieApiService.getPopularMovies(1)
        }.last() is BaseApiDataState.Success

        assertEquals(isSuccess,true)

    }

    @Test
    @Timeout(value = 60, unit = TimeUnit.SECONDS)
    fun callApiError() = runTest {

        val isError = callApi {
            mMovieApiService.getPopularMovies(1)
        }.last() is BaseApiDataState.Error

        assertEquals(isError,true)

    }

    @Test
    @Timeout(value = 60, unit = TimeUnit.SECONDS)
    fun callApiLoading() = runTest {

        val isLoading = callApi {
            mMovieApiService.getPopularMovies(1)
        }.first() is BaseApiDataState.Loading

        assertEquals(isLoading,true)

    }


}