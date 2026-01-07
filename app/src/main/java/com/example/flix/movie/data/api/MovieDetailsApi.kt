package com.example.flix.movie.data.api

import com.example.flix.movie.data.model.cast.CastResponse
import com.example.flix.movie.data.model.movie.MovieModel
import com.example.flix.movie.data.model.movie_media.MovieMediaResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieDetailsApi {
    @GET("movie/{movie_id}")
    suspend fun getMovie(
        @Path("movie_id") movieId: Int
    ): MovieModel

    @GET("movie/{movie_id}/videos")
    suspend fun getTrailer(
        @Path("movie_id") movieId: Int
    ): MovieMediaResponse

    @GET("movie/{movie_id}/credits")
    suspend fun getCast(
        @Path("movie_id") movieId: Int
    ): CastResponse
}