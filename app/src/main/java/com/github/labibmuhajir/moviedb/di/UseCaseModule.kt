package com.github.labibmuhajir.moviedb.di

import com.github.labibmuhajir.moviedb.data.MovieUseCase
import com.github.labibmuhajir.moviedb.data.MovieUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {
    @Binds
    abstract fun provideMovieUseCase(movieUseCaseImpl: MovieUseCaseImpl): MovieUseCase
}