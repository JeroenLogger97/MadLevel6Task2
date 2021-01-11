package com.example.madlevel6task2.model

data class ImageItem(
    var imageId: String
) {
    fun getImageUrl() = "https://image.tmdb.org/t/p/original$imageId"
}