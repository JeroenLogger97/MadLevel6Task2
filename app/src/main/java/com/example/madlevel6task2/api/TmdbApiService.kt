package com.example.madlevel6task2.api

import com.example.madlevel6task2.model.MovieList
import retrofit2.http.GET
import retrofit2.http.Query

interface TmdbApiService {

    @GET("/3/discover/movie" +
            "?api_key=8ebb840e2b3bf3572173579ba30e810b" +
            "&language=en-US" +
            "&sort_by=vote_average.desc" +
//            "&sort_by=popularity.desc" +
            "&include_adult=false" +
            "&include_video=false&page=1" +
            "&vote_count.gte=300" +
            "&with_original_language=en" +
            "&include_image_language=en,null" +
            "&append_to_response=images")
    suspend fun getPopularMoviesOfYear(@Query("primary_release_year") year: Int): MovieList

}