package com.mina.movie.api

import com.mina.movie.model.remotemoviesmodel.MoviesDataModel
import com.mina.movie.utilies.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ServiceAPI {
    // https://api.themoviedb.org/3/movie/latest?api_key=$API_KEY&language=en-US
    // connection to return latest movies
    //note : the latest API return only one movie or null because of that i use trending API to complete the task

    @GET("trending/movie/week?api_key=${Constants.API_KEY}&language=en-US")
    suspend fun getLatestMoviesData(
        @Query("page") page: Int
    ): Response<MoviesDataModel>

    //connection to return search movies depend on user query
    @GET("search/movie?api_key=${Constants.API_KEY}")
    suspend fun getSearchMovies(
        @Query("query") query: String,
        @Query("page") page: Int

    ): Response<MoviesDataModel>

}