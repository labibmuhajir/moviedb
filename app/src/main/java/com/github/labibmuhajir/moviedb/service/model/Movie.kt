package com.github.labibmuhajir.moviedb.service.model

import androidx.room.PrimaryKey
import com.github.labibmuhajir.moviedb.data.local.BannerMovie
import com.github.labibmuhajir.moviedb.data.local.PopularMovie
import com.github.labibmuhajir.moviedb.data.local.UpcomingMovie
import com.google.gson.annotations.SerializedName

data class Movie(
    val adult: Boolean?,
    @SerializedName("backdrop_path")
    val backdropPath: String?,
    @PrimaryKey
    val id: Int,
    @SerializedName("media_type")
    val mediaType: String?,
    @SerializedName("original_language")
    val originalLanguage: String?,
    @SerializedName("original_title")
    val originalTitle: String?,
    val overview: String?,
    val popularity: Double?,
    @SerializedName("poster_path")
    val posterPath: String?,
    @SerializedName("release_date")
    val releaseDate: String?,
    val title: String?,
    val video: Boolean?,
    @SerializedName("vote_average")
    val voteAverage: Double?,
    @SerializedName("vote_count")
    val voteCount: Int?
)

fun Movie.toBannerMovie(): BannerMovie {
    return BannerMovie(
        adult,
        backdropPath,
        id,
        mediaType,
        originalLanguage,
        originalTitle,
        overview,
        popularity,
        posterPath,
        releaseDate,
        title,
        video,
        voteAverage,
        voteCount
    )
}

fun Movie.toPopularMovie(): PopularMovie {
    return PopularMovie(
        adult,
        backdropPath,
        id,
        mediaType,
        originalLanguage,
        originalTitle,
        overview,
        popularity,
        posterPath,
        releaseDate,
        title,
        video,
        voteAverage,
        voteCount
    )
}

fun Movie.toUpcomingMovie(): UpcomingMovie {
    return UpcomingMovie(
        adult,
        backdropPath,
        id,
        mediaType,
        originalLanguage,
        originalTitle,
        overview,
        popularity,
        posterPath,
        releaseDate,
        title,
        video,
        voteAverage,
        voteCount
    )
}