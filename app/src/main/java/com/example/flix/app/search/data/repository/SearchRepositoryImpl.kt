package com.example.flix.app.search.data.repository

import com.example.flix.app.search.data.api.SearchApi
import com.example.flix.app.search.data.model.SearchModel
import javax.inject.Inject


class SearchRepositoryImpl @Inject constructor(
    private val searchApi: SearchApi
) : SearchRepository {
    override suspend fun searchMovie(query: String): SearchModel {
        return searchApi.SearchMovie(query)
    }
}