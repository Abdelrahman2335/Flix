package com.example.flix.search.data.api

import com.example.flix.search.data.model.SearchModel
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApi {

    @GET("search/movie")
    suspend fun searchMovie(
        @Query("query") query: String
    ): SearchModel
}