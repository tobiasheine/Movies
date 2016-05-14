package com.movies.tobi.popularmovies.posterdetails;

import com.movies.tobi.popularmovies.BuildConfig;
import com.movies.tobi.popularmovies.backend.Backend;

import rx.Observable;

public class MovieDetailsApiDatasource {

    private final Backend backend;

    public MovieDetailsApiDatasource(Backend backend) {
        this.backend = backend;
    }

    public Observable<ApiMovieDetails> getMovieDetails(long movieId) {
        return backend.movieDetails(movieId, BuildConfig.API_KEY);
    }
}
