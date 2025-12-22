package com.example.flix.app.home.core.api_service

import com.example.flix.app.home.data.model.GenreResponse
import com.example.flix.app.home.data.model.Movie
import com.example.flix.app.home.data.model.MoviesResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface MovieApi {

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("page") page: Int = 1,
    ): MoviesResponse

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movieId: Int,
    ): Movie

    @GET("genre/movie/list")
    suspend fun getMovieGenres(): GenreResponse

    @GET("discover/movie")
    suspend fun discoverMovies(
        @Query("with_genres") genreId: Int,
        @Query("page") page: Int = 1
    ): MoviesResponse

}