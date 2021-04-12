package com.github.labibmuhajir.moviedb.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [BannerMovie::class, PopularMovie::class, UpcomingMovie::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun bannerDao(): BannerMovieDao
    abstract fun popularDao(): PopularMovieDao
    abstract fun upcomingDao(): UpcomingMovieDao
}