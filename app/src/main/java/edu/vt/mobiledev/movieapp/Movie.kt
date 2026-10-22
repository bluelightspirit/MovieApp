package edu.vt.mobiledev.movieapp

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class Movie(

    @PrimaryKey
    val id: Int,

    val title: String,
    val overview: String?,
    val poster_path: String?,
    val release_date: String,
    val vote_average: Double,
    val vote_count: Int,
    val original_language: String,

    val isFavorite: Boolean = false,

    val photoPath: String? = null
)