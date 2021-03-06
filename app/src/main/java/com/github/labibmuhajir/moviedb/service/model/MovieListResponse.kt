package com.github.labibmuhajir.moviedb.service.model

import com.google.gson.annotations.SerializedName

data class MovieListResponse(
    val `public`: Boolean?,
    @SerializedName("average_rating")
    val averageRating: Double?,
    @SerializedName("backdrop_path")
    val backdropPath: String?,
    val description: String?,
    val id: Int?,
    val iso_3166_1: String?,
    val iso_639_1: String?,
    val name: String?,
    val page: Int?,
    @SerializedName("poster_path")
    val posterPath: String?,
    val results: List<Movie>?,
    val revenue: Long?,
    val runtime: Int?,
    @SerializedName("sort_by")
    val sortBy: String?,
    @SerializedName("total_pages")
    val totalPages: Int?,
    @SerializedName("total_results")
    val totalResults: Int?
)