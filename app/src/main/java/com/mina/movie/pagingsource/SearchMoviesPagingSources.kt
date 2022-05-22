package com.mina.movie.pagingsource

import androidx.paging.PagingSource
import com.mina.movie.api.ServiceAPI
import com.mina.movie.model.remotemoviesmodel.Movie
import retrofit2.HttpException
import java.io.IOException

private const val PAGE_INDEX = 1

class PagingSourcesSearchMovies(private val api: ServiceAPI, private val query: String) :
    PagingSource<Int, Movie>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        // the start page number=1
        val position = params.key ?: PAGE_INDEX
        return try {
            val response = api.getSearchMovies(query, position)
            LoadResult.Page(
                data = response.results,
                prevKey = if (position == PAGE_INDEX) null else position - 1,
                nextKey = if (response.results.isEmpty()) null else position + 1
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)

        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }

    }
}