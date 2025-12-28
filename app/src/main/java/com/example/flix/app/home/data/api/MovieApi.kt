package com.example.flix.app.home.data.api

import com.example.flix.app.home.data.model.GenreResponse
import com.example.flix.app.home.data.model.PopularMoviesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("page") page: Int = 1,
    ): PopularMoviesResponse

    @GET("genre/movie/list")
    suspend fun getMovieGenres(): GenreResponse

    @GET("discover/movie")
    suspend fun discoverMovies(
        @Query("with_genres") genreId: Int,
        @Query("page") page: Int = 1
    ): PopularMoviesResponse

}