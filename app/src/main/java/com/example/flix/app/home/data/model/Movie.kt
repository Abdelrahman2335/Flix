package com.example.flix.app.home.data.model


import com.google.gson.annotations.SerializedName

data class Movie(
    val adult: Boolean = false,
    @SerializedName("backdrop_path") val backdropPath: String? = null,
    @SerializedName("belongs_to_collection") val belongsToCollection: BelongsToCollection? = null,
    val budget: Int = 0,
    val genres: List<Genre>? = null,
    @SerializedName("genre_ids") val genreIds: List<Int>? = null,
    val homepage: String? = null,
    val id: Int,
    @SerializedName("imdb_id") val imdbId: String? = null,
    @SerializedName("origin_country") val originCountry: List<String> = emptyList(),
    @SerializedName("original_language") val originalLanguage: String = "",
    @SerializedName("original_title") val originalTitle: String = "",
    val overview: String = "",
    val popularity: Double = 0.0,
    @SerializedName("poster_path") val posterPath: String? = null,
    @SerializedName("production_companies") val productionCompanies: List<ProductionCompany> = emptyList(),
    @SerializedName("production_countries") val productionCountries: List<ProductionCountry> = emptyList(),
    @SerializedName("release_date") val releaseDate: String = "",
    val revenue: Int = 0,
    val runtime: Int = 0,
    @SerializedName("spoken_languages") val spokenLanguages: List<SpokenLanguage> = emptyList(),
    val status: String = "",
    val tagline: String = "",
    val title: String = "",
    val video: Boolean = false,
    @SerializedName("vote_average") val voteAverage: Double = 0.0,
    @SerializedName("vote_count") val voteCount: Int = 0
)

data class BelongsToCollection(
    @SerializedName("backdrop_path") val backdropPath: String? = null,
    val id: Int,
    val name: String = "",
    @SerializedName("poster_path") val posterPath: String? = null
)

data class Genre(
    val id: Int,
    val name: String = ""
)

data class ProductionCompany(
    val id: Int,
    @SerializedName("logo_path") val logoPath: String? = null,
    val name: String = "",
    @SerializedName("origin_country") val originCountry: String = ""
)

data class ProductionCountry(
    @SerializedName("iso_3166_1") val iso31661: String = "",
    val name: String = ""
)

data class SpokenLanguage(
    @SerializedName("english_name") val englishName: String = "",
    @SerializedName("iso_639_1") val iso6391: String = "",
    val name: String = ""
)