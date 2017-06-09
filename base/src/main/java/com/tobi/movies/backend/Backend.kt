package com.tobi.movies.backend

import com.tobi.movies.popularstream.ApiPopularMoviesResponse
import com.tobi.movies.posterdetails.ApiMovieDetails

import retrofit2.http.GET
import retrofit2.http.Path
import rx.Observable

interface Backend {

    @GET("/3/movie/popular")
    fun popularStream(): Observable<ApiPopularMoviesResponse>

    @GET("/3/movie/{id}")
    fun movieDetails(@Path("id") movieId: Long): Observable<ApiMovieDetails>

    companion object {

        val SERVICE_ENDPOINT = "https://api.themoviedb.org/"
    }
}
