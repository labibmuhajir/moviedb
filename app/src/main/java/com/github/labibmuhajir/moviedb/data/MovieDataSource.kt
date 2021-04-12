package com.github.labibmuhajir.moviedb.data

import com.github.labibmuhajir.moviedb.data.local.BannerMovieDao
import com.github.labibmuhajir.moviedb.data.local.PopularMovieDao
import com.github.labibmuhajir.moviedb.data.local.UpcomingMovieDao
import com.github.labibmuhajir.moviedb.service.Service
import com.github.labibmuhajir.moviedb.service.model.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface MovieDataSource {
    suspend fun getPopularMovie(page: Int = 1): MovieListResponse
    suspend fun getUpcomingMovie(page: Int = 1): MovieListResponse
    suspend fun discoverMovie(page: Int = 1): MovieSearchResponse
}

class MovieRepository @Inject constructor(
    private val service: Service,
    private val bannerMovieDao: BannerMovieDao,
    private val popularMovieDao: PopularMovieDao,
    private val upcomingMovieDao: UpcomingMovieDao
) : MovieDataSource {
    override suspend fun getPopularMovie(page: Int): MovieListResponse {
        return withContext(Dispatchers.IO) {
            val data = service.getPopularMovie(page)
            val movies = data.results
            if (movies != null) {
                val updated = movies.map { it.toPopularMovie() }
                popularMovieDao.insertAll(updated)
            }
            data
        }
    }

    override suspend fun getUpcomingMovie(page: Int): MovieListResponse {
        return withContext(Dispatchers.IO) {
            val data = service.getUpcomingMovie(page)
            val movies = data.results
            if (movies != null) {
                val updated = movies.map { it.toUpcomingMovie() }
                upcomingMovieDao.insertAll(updated)
            }
            data
        }
    }

    override suspend fun discoverMovie(page: Int): MovieSearchResponse {
        return withContext(Dispatchers.IO) {
            val data = service.getDiscoverMovie(page)
            val movies = data.results
            if (movies != null) {
                val updated = movies.map { it.toBannerMovie() }
                bannerMovieDao.insertAll(updated)
            }
            data
        }
    }

}