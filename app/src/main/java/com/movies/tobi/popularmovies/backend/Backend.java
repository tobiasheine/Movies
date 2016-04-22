package com.movies.tobi.popularmovies.backend;

import com.movies.tobi.popularmovies.popularstream.ApiPopularMoviesResponse;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface Backend {

    String SERVICE_ENDPOINT = "https://api.themoviedb.org/";

    @GET("/3/movie/popular")
    Observable<ApiPopularMoviesResponse> popularStream(@Query("api_key") String apiKey);
}
