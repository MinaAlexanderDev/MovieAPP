package com.mina.movie.pagingsource

import android.util.Log
import androidx.paging.PagingSource
import com.mina.movie.api.ServiceAPI
import com.mina.movie.model.remotemoviesmodel.Movie
import com.mina.movie.repository.RepositoryImp
import retrofit2.HttpException
import java.io.IOException

private const val PAGE_INDEX = 1

class PagingSourcesLatestMovies(private val api: ServiceAPI) :
    PagingSource<Int, Movie>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        // the start page number=1
        val position = params.key ?: PAGE_INDEX

        return try {
            val response = api.getLatestMoviesData(position)
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