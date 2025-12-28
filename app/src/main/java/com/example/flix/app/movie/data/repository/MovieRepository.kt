package com.example.flix.app.movie.data.repository

import com.example.flix.app.movie.data.model.cast.CastResponse
import com.example.flix.app.movie.data.model.movie.MovieModel
import com.example.flix.app.movie.data.model.movie_media.MovieMediaResponse

interface MovieRepository {
    suspend fun getMovieDetails(id: Int): MovieModel
    suspend fun getMovieTrailer(id: Int): MovieMediaResponse
    suspend fun getCast(id: Int): CastResponse
}