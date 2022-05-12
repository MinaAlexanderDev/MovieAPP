package com.mina.movie.remotedata

import com.mina.movie.api.ServiceAPI
import com.mina.movie.model.remotemoviesmodel.MoviesDataModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class RemoteRepositoryImp @Inject constructor(private val api: ServiceAPI) : RemoteRepository {

    //get the latest movies from server
    override suspend fun getLatestMovies(page: Int): Response<MoviesDataModel> =
        withContext(Dispatchers.IO) {
            api.getLatestMoviesData(page)
        }

    //get the search movies from server
    override suspend fun getSearchMovies(
        query: String,
        page: Int
    ): Response<MoviesDataModel> = withContext(Dispatchers.IO) {
        api.getSearchMovies(query, page)
    }

}