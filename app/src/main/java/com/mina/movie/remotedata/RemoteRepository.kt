package com.mina.movie.remotedata

import com.mina.movie.model.remotemoviesmodel.MoviesDataModel
import retrofit2.Response

interface RemoteRepository {

    //  interface of the latest movies from API
    suspend fun getLatestMovies(page: Int): Response<MoviesDataModel>

    //   interface of the search movies depend on user query  from API
    suspend fun getSearchMovies(
        query: String,
        page: Int
    ): Response<MoviesDataModel>

}