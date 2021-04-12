package com.github.labibmuhajir.moviedb.di

import com.github.labibmuhajir.moviedb.data.MovieDataSource
import com.github.labibmuhajir.moviedb.data.MovieRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindMovieDataSource(movieRepository: MovieRepository): MovieDataSource
}