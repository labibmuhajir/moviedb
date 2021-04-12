package com.github.labibmuhajir.moviedb.di

import android.content.Context
import androidx.room.Room
import com.github.labibmuhajir.moviedb.data.local.AppDatabase
import com.github.labibmuhajir.moviedb.data.local.BannerMovieDao
import com.github.labibmuhajir.moviedb.data.local.PopularMovieDao
import com.github.labibmuhajir.moviedb.data.local.UpcomingMovieDao
import com.github.labibmuhajir.moviedb.service.NetworkChecker
import com.github.labibmuhajir.moviedb.service.Service
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import moviedb.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    private val baseUrl = "https://api.themoviedb.org"
    private val apiKey = "b26733daf3a5f7fd722800d1110e79b8"
    val imageUrl = "https://image.tmdb.org/t/p/"

    @Provides
    fun provideInterceptor(): Interceptor = Interceptor { chain ->
        val original = chain.request()
        val url = original.url.newBuilder()
            .addQueryParameter("api_key", apiKey)
            .build()
        val request = original.newBuilder().url(url).build()

        chain.proceed(request)
    }

    @Provides
    fun provideClient(interceptor: Interceptor): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build()

    @Provides
    fun provideConverter(): GsonConverterFactory = GsonConverterFactory.create()

    @Provides
    fun provideRetrofit(client: OkHttpClient, converter: GsonConverterFactory): Retrofit =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(converter)
            .build()

    @Provides
    fun provideService(retrofit: Retrofit): Service = retrofit.create(Service::class.java)

    @Provides
    fun provideAppDatabase(@ApplicationContext applicationContext: Context): AppDatabase {
        return Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "${BuildConfig.APPLICATION_ID}.moviedb"
        ).build()
    }

    @Provides
    fun provideBannerMovie(appDatabase: AppDatabase): BannerMovieDao {
        return appDatabase.bannerDao()
    }

    @Provides
    fun providePopularMovie(appDatabase: AppDatabase): PopularMovieDao {
        return appDatabase.popularDao()
    }

    @Provides
    fun provideUpcomingMovie(appDatabase: AppDatabase): UpcomingMovieDao {
        return appDatabase.upcomingDao()
    }

    @Provides
    fun provideNetworkCheck(@ApplicationContext context: Context): NetworkChecker {
        return NetworkChecker(context)
    }
}