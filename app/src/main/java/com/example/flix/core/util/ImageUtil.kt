package com.example.flix.core.util


private const val BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w500"


fun getImageUrl(posterPath: String?): String {
    return if (posterPath != null) {
        "$BASE_IMAGE_URL$posterPath"
    } else {
        "" // Return empty string for null paths
    }
}