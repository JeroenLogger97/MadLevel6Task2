package com.example.madlevel6task2.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.madlevel6task2.api.TmdbApi
import com.example.madlevel6task2.api.TmdbApiService
import com.example.madlevel6task2.model.Movie
import com.example.madlevel6task2.model.MovieList
import kotlinx.coroutines.withTimeout

class MovieRepository {

    private val tmdbApiService: TmdbApiService = TmdbApi.createApi()

    private val _movies: MutableLiveData<MovieList> = MutableLiveData()

    val movies: LiveData<MovieList>
        get() = _movies

    suspend fun getPopularMoviesOfYear(year: Int)  {
        try {
            // timeout the request after 5 seconds
            val result = withTimeout(5_000) {
                tmdbApiService.getPopularMoviesOfYear(year)
            }

            _movies.value = result
        } catch (error: Throwable) {
            throw MovieError("Unable to get movies", error)
        }
    }

    class MovieError(message: String, cause: Throwable) : Throwable(message, cause)
}