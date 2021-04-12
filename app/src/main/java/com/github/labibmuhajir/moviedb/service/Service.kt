package com.github.labibmuhajir.moviedb.service

import com.github.labibmuhajir.moviedb.service.model.MovieListResponse
import com.github.labibmuhajir.moviedb.service.model.MovieSearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface Service {
    @GET("/3/movie/popular")
    suspend fun getPopularMovie(@Query("page") page: Int): MovieListResponse

    @GET("/3/movie/upcoming")
    suspend fun getUpcomingMovie(@Query("page") page: Int): MovieListResponse

    @GET("/3/discover/movie")
    suspend fun getDiscoverMovie(@Query("page") page: Int): MovieSearchResponse
}