package com.example.flix.app.home.data.repository

import com.example.flix.app.home.data.model.GenreResponse
import com.example.flix.app.home.data.model.PopularMoviesResponse
import com.example.flix.app.home.domain.repository.HomeRepository
import com.example.flix.core.api_service.MovieApi
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

