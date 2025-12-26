package com.example.flix.app.home.domain.repository

import com.example.flix.app.home.data.model.GenreResponse
import com.example.flix.app.home.data.model.MoviesResponse

interface HomeRepository {
    suspend fun getPopularMovies(page: Int): MoviesResponse
    suspend fun getMovieGenres(): GenreResponse
    suspend fun discoverMovies(genreId: Int): MoviesResponse
}

