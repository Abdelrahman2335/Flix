package com.example.flix.search.data.model

data class SearchModel(
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)