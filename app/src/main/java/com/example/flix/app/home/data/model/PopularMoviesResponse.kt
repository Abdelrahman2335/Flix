package com.example.flix.app.home.data.model

import com.google.gson.annotations.SerializedName

data class PopularMoviesResponse(
    val page: Int,
    val results: List<MovieResponse>,
    @SerializedName("total_pages") val totalPages: Int,
    @SerializedName("total_results") val totalResults: Int
)

