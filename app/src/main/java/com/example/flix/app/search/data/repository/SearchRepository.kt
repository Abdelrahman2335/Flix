package com.example.flix.app.search.data.repository

import com.example.flix.app.search.data.model.SearchModel

interface SearchRepository {
    suspend fun searchMovie(query: String): SearchModel
}