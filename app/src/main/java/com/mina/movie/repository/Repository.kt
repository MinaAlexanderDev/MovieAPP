package com.mina.movie.repository

import com.mina.movie.model.remotemoviesmodel.MoviesDataModel
import retrofit2.Response

interface Repository {
    //get latest movies from API
    suspend fun getLatestMoviesData(page: Int): Response<MoviesDataModel>

    //get search movies from API
    suspend fun getSearchMovies(
        query: String,
        page: Int
    ): Response<MoviesDataModel>

}