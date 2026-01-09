package com.example.flix.movie.data.repository

import com.example.flix.movie.data.model.cast.CastResponse
import com.example.flix.movie.data.model.movie.MovieModel
import com.example.flix.movie.data.model.movie_media.MovieMediaResponse

interface MovieRepository {
    suspend fun getMovieDetails(id: Int): MovieModel
    suspend fun getCast(id: Int): CastResponse
    suspend fun getMovieVideos(movieId: Int): MovieMediaResponse
}