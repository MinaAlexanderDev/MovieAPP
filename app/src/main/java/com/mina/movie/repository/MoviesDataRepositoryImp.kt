package com.mina.movie.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.mina.movie.pagingsource.PagingSourcesLatestMovies
import com.mina.movie.pagingsource.PagingSourcesSearchMovies
import javax.inject.Inject

class MoviesDataRepositoryImp @Inject constructor(private val api: RepositoryImp) :
    MoviesDataRepository {
    //data return from paging source as livedata for latest movie
    override fun getLatestDataResults() =
        Pager(
            config = PagingConfig(
                pageSize = 10,
                maxSize = 30,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { PagingSourcesLatestMovies(api) }
        ).liveData

    //data return from paging source as livedata for search movie
    override fun getSearchDataResults(query: String) =
        Pager(
            config = PagingConfig(

                pageSize = 10,
                maxSize = 30,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { PagingSourcesSearchMovies(api, query) }
        ).liveData


}
