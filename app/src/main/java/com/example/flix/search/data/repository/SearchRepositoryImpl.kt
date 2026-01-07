package com.example.flix.search.data.repository

import com.example.flix.search.data.api.SearchApi
import com.example.flix.search.data.model.SearchModel
import javax.inject.Inject


class SearchRepositoryImpl @Inject constructor(
    private val searchApi: SearchApi
) : SearchRepository {
    override suspend fun searchMovie(query: String): SearchModel {
        return searchApi.SearchMovie(query)
    }
}