package edu.vt.mobiledev.movieapp

data class MovieResponse(
    val page: Int,
    val results: List<Movie>
)