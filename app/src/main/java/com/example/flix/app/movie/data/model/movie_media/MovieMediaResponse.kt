package com.example.flix.app.movie.data.model.movie_media

import com.google.gson.annotations.SerializedName

data class MovieMediaResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("results")
    val results: List<Result>
)