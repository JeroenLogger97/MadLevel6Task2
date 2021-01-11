package com.example.madlevel6task2.model

data class MovieList(
    val page: Int,
    val results: List<Movie>,
    val totalResults: Int,
    val totalPages: Int
)