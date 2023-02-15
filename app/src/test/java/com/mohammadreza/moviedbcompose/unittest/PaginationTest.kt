package com.mohammadreza.moviedbcompose.unittest

import androidx.paging.PagingSource
import com.mohammadreza.moviedbcompose.data.api.MovieApiService
import com.mohammadreza.moviedbcompose.ui.screens.popular.PopularPagingSource
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

/**
 * Create by Mohammadreza Allahgholi
 *  Site: https://seniorandroid.ir
 */
class PaginationTest {

    private val mMovieApiService = mockk<MovieApiService>(relaxed = true)
    private lateinit var popularPagingSource: PopularPagingSource

    @BeforeEach
    fun setup() {
        popularPagingSource = PopularPagingSource(mMovieApiService)
    }

    @Test
    fun testPopularViewModel() = runTest {

        val result = popularPagingSource.load(
            PagingSource.LoadParams.Refresh(
                key = 2,
                loadSize = 2,
                placeholdersEnabled = false
            )
        ) is PagingSource.LoadResult.Page


        assertEquals(result, true)

    }


}