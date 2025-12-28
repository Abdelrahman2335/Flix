package com.example.flix.app.search.data.api

import com.example.flix.app.search.data.model.SearchModel
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApi {

    @GET("search/movie")
    suspend fun SearchMovie(
        @Query("query") query: String
    ): SearchModel
}