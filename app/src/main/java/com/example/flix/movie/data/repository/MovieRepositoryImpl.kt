package com.example.flix.movie.data.repository

import com.example.flix.movie.data.api.MovieDetailsApi
import com.example.flix.movie.data.model.cast.CastResponse
import com.example.flix.movie.data.model.movie.MovieModel
import com.example.flix.movie.data.model.movie_media.MovieMediaResponse
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