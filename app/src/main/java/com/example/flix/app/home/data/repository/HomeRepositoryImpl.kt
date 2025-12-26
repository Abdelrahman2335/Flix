package com.example.flix.app.home.data.repository

import com.example.flix.app.home.core.api_service.MovieApi
import com.example.flix.app.home.data.model.GenreResponse
import com.example.flix.app.home.data.model.MoviesResponse
import com.example.flix.app.home.domain.repository.HomeRepository
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val movieApi: MovieApi
) : HomeRepository {

    override suspend fun getPopularMovies(page: Int): MoviesResponse {
        return movieApi.getPopularMovies(page)
    }

    override suspend fun getMovieGenres(): GenreResponse {
        return movieApi.getMovieGenres()
    }

    override suspend fun discoverMovies(genreId: Int): MoviesResponse {
        return movieApi.discoverMovies(genreId)
    }
}

