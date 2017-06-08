package com.tobi.movies.backend;

import com.tobi.movies.popularstream.ApiPopularMoviesResponse;
import com.tobi.movies.posterdetails.ApiMovieDetails;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

public interface Backend {

    String SERVICE_ENDPOINT = "https://api.themoviedb.org/";

    @GET("/3/movie/popular")
    Observable<ApiPopularMoviesResponse> popularStream();

    @GET("/3/movie/{id}")
    Observable<ApiMovieDetails> movieDetails(@Path("id") long movieId);
}
