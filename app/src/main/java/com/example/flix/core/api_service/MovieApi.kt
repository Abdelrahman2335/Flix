package com.example.flix.app.home.core.api_service

import com.example.flix.app.home.data.model.GenreResponse
import com.example.flix.app.home.data.model.MovieResponse
import com.example.flix.app.home.data.model.PopularMoviesResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface MovieApi {

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("page") page: Int = 1,
    ): PopularMoviesResponse

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movieId: Int,
    ): MovieResponse

    @GET("genre/movie/list")
    suspend fun getMovieGenres(): GenreResponse
}