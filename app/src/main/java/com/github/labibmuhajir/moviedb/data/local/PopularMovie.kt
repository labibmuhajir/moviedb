package com.github.labibmuhajir.moviedb.data.local

import androidx.room.*
import com.github.labibmuhajir.moviedb.service.model.Movie

@Entity
data class PopularMovie(
    val adult: Boolean?,
    @ColumnInfo(name = "backdrop_path")
    val backdropPath: String?,
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "media_type")
    val mediaType: String?,
    @ColumnInfo(name = "original_language")
    val originalLanguage: String?,
    @ColumnInfo(name = "original_title")
    val originalTitle: String?,
    val overview: String?,
    val popularity: Double?,
    @ColumnInfo(name = "poster_path")
    val posterPath: String?,
    @ColumnInfo(name = "release_date")
    val releaseDate: String?,
    val title: String?,
    val video: Boolean?,
    @ColumnInfo(name = "vote_average")
    val voteAverage: Double?,
    @ColumnInfo(name = "vote_countMov")
    val voteCount: Int?
)

fun PopularMovie.toMovie(): Movie {
    return Movie(
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

@Dao
interface PopularMovieDao {
    @Query("SELECT * FROM popularmovie")
    fun getAll(): List<PopularMovie>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(movies: PopularMovie)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(movies: List<PopularMovie>)

    @Delete
    fun delete(movie: PopularMovie)

    @Query("DELETE FROM popularmovie")
    fun clearTable()
}