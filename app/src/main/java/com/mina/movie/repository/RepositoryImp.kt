package com.mina.movie.repository

import com.mina.movie.api.ServiceAPI
import com.mina.movie.model.remotemoviesmodel.MoviesDataModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class RepositoryImp @Inject constructor(private val serviceAPI: ServiceAPI) : ServiceAPI {

override suspend fun getLatestMoviesData(page: Int):MoviesDataModel =
        withContext(Dispatchers.IO) {
            serviceAPI.getLatestMoviesData(page)
    }

    //get the search movies from server
    override suspend fun getSearchMovies(
        query: String,
        page: Int
    ): MoviesDataModel= withContext(Dispatchers.IO) {
        serviceAPI.getSearchMovies(query, page)
    }

}