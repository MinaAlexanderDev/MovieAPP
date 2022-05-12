package com.mina.movie.ui.viewmodel

import androidx.lifecycle.SavedStateHandle
import com.mina.movie.repository.MoviesDataRepositoryTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class MoviesViewModelTest {
    private lateinit var viewModel: MoviesViewModel

    @Before
    fun setup() {
        viewModel = MoviesViewModel(MoviesDataRepositoryTest(), state = SavedStateHandle())
    }

    @Test
    fun `insert shopping item with empty field, returns movies`() {
        val result = viewModel.latestMoviesData
        Assert.assertNotNull(result)
    }

    @Test
    fun `insert shopping item with empty field, returns null`() {
        val result = viewModel.searchMoviesData
        Assert.assertNotNull(result)
    }
}