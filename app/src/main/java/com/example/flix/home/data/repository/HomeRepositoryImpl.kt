package com.example.flix.home.data.repository

import com.example.flix.home.data.api.MovieApi
import com.example.flix.home.data.model.GenreResponse
import com.example.flix.home.data.model.PopularMoviesResponse
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val movieApi: MovieApi
) : HomeRepository {

    override suspend fun getPopularMovies(page: Int): PopularMoviesResponse {
        return movieApi.getPopularMovies(page)
    }

    override suspend fun getMovieGenres(): GenreResponse {
        return movieApi.getMovieGenres()
    }

    override suspend fun discoverMovies(genreId: Int): PopularMoviesResponse {
        return movieApi.discoverMovies(genreId)
    }
}

