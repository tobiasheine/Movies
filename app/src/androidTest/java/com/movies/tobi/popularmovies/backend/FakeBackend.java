package com.movies.tobi.popularmovies.backend;

import com.movies.tobi.popularmovies.popularstream.ApiPopularMoviesResponse;
import com.movies.tobi.popularmovies.posterdetails.ApiMovieDetails;

import java.util.HashMap;
import java.util.Map;

import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

public class FakeBackend implements Backend {

    private final Map<Long, ApiMovieDetails> movieDetails = new HashMap<>();

    public void addMovieDetails(long movieId, ApiMovieDetails apiMovieDetails) {
        movieDetails.put(movieId, apiMovieDetails);
    }

    @Override
    public Observable<ApiPopularMoviesResponse> popularStream(@Query("api_key") String apiKey) {
        throw new IllegalStateException("not yet implemented");
    }

    @Override
    public Observable<ApiMovieDetails> movieDetails(@Path("id") long movieId, @Query("api_key") String apiKey) {
        return Observable.just(movieDetails.get(movieId));
    }
}
