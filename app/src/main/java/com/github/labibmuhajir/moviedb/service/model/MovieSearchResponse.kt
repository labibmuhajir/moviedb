package com.github.labibmuhajir.moviedb.service.model

import com.google.gson.annotations.SerializedName

data class MovieSearchResponse(
    val page: Int?,
    val results: List<Movie>?,
    @SerializedName("total_pages")
    val totalPages: Int?,
    @SerializedName("total_results")
    val totalResults: Int?
)