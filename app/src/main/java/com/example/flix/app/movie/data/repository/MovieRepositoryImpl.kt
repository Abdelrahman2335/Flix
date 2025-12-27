package com.example.flix.app.movie.data.repository

import com.example.flix.app.movie.data.model.cast.CastResponse
import com.example.flix.app.movie.data.model.movie.MovieModel
import com.example.flix.app.movie.data.model.movie_media.MovieMediaResponse
import com.example.flix.app.movie.domin.repository.MovieRepository
import com.example.flix.core.api_service.MovieDetailsApi
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieDetailsApi: MovieDetailsApi
) : MovieRepository {
    override suspend fun getMovieDetails(id: Int): MovieModel {
        return movieDetailsApi.getMovie(id)
    }

    override suspend fun getMovieTrailer(id: Int): MovieMediaResponse {
        return movieDetailsApi.getTrailer(id)
    }

    override suspend fun getCast(id: Int): CastResponse {
        return movieDetailsApi.getCast(id)
    }
}