package com.mina.movie.repository

import com.mina.movie.model.remotemoviesmodel.MoviesDataModel
import com.mina.movie.remotedata.RemoteRepository
import retrofit2.Response
import javax.inject.Inject

class RepositoryImp @Inject constructor(
    private val remoteRepository: RemoteRepository,
) : Repository {

    override suspend fun getLatestMoviesData(page: Int): Response<MoviesDataModel> {
        return remoteRepository.getLatestMovies(page)
    }

    override suspend fun getSearchMovies(query: String, page: Int): Response<MoviesDataModel> {
        return remoteRepository.getSearchMovies(query, page)
    }
}