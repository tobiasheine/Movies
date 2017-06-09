package com.tobi.movies.posterdetails


import org.joda.time.LocalDate

data class MovieDetails(
        val overview: String,
        val movieId: Long,
        val originalTitle: String,
        val posterPath: String,
        val releaseDate: LocalDate)


