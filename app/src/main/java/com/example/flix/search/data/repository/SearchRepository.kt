package com.example.flix.search.data.repository

import com.example.flix.search.data.model.SearchModel

interface SearchRepository {
    suspend fun searchMovie(query: String): SearchModel
}