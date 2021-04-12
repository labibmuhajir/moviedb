package com.github.labibmuhajir.moviedb.data

import com.github.labibmuhajir.moviedb.data.local.BannerMovieDao
import com.github.labibmuhajir.moviedb.data.local.PopularMovieDao
import com.github.labibmuhajir.moviedb.data.local.UpcomingMovieDao
import com.github.labibmuhajir.moviedb.data.local.toMovie
import com.github.labibmuhajir.moviedb.service.NetworkChecker
import com.github.labibmuhajir.moviedb.service.model.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface MovieUseCase {
    suspend fun getPopularMovie(page: Int = 1): List<Movie>
    suspend fun getUpcomingMovie(page: Int = 1): List<Movie>
    suspend fun discoverMovie(page: Int = 1): List<Movie>
}

class MovieUseCaseImpl @Inject constructor(
    private val movieDataSource: MovieDataSource,
    private val bannerMovieDao: BannerMovieDao,
    private val popularMovieDao: PopularMovieDao,
    private val upcomingMovieDao: UpcomingMovieDao,
    private val networkChecker: NetworkChecker
) : MovieUseCase {
    override suspend fun getPopularMovie(page: Int): List<Movie> {
       return withContext(Dispatchers.IO) {
           if (networkChecker.isNetworkAvailable()) {
               movieDataSource.getPopularMovie(page).results ?: listOf()
           } else {
               popularMovieDao.getAll().map { it.toMovie() }
           }
       }
    }

    override suspend fun getUpcomingMovie(page: Int): List<Movie> {
        return withContext(Dispatchers.IO) {
            if (networkChecker.isNetworkAvailable()) {
                movieDataSource.getUpcomingMovie(page).results ?: listOf()
            } else {
                upcomingMovieDao.getAll().map { it.toMovie() }
            }
        }
    }

    override suspend fun discoverMovie(page: Int): List<Movie> {
        return withContext(Dispatchers.IO) {
            if (networkChecker.isNetworkAvailable()) {
                movieDataSource.discoverMovie(page).results ?: listOf()
            } else {
                bannerMovieDao.getAll().map { it.toMovie() }
            }
        }
    }

}