package com.example.flix.app.home.data.repository

import com.example.flix.app.home.data.model.GenreResponse
import com.example.flix.app.home.data.model.PopularMoviesResponse

interface HomeRepository {
    suspend fun getPopularMovies(page: Int): PopularMoviesResponse
    suspend fun getMovieGenres(): GenreResponse
    suspend fun discoverMovies(genreId: Int): PopularMoviesResponse
}