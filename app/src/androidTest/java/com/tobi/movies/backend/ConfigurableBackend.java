package com.tobi.movies.backend;

import android.support.test.espresso.core.deps.guava.collect.Lists;

import com.tobi.movies.popularstream.ApiMoviePoster;
import com.tobi.movies.popularstream.ApiPopularMoviesResponse;
import com.tobi.movies.popularstream.PublicApiPopularMoviesResponse;
import com.tobi.movies.posterdetails.ApiMovieDetails;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

public class ConfigurableBackend implements Backend {

    private final Map<Long, ApiMovieDetails> movieDetails = new HashMap<>();
    private final List<ApiMoviePoster> popularStream = Lists.newArrayList();

    public void addMovieDetails(ApiMovieDetails apiMovieDetails) {
        movieDetails.put(apiMovieDetails.movieId, apiMovieDetails);
    }

    public void addToPopularStream(ApiMoviePoster... movieDetails) {
        popularStream.addAll(Lists.newArrayList(movieDetails));
    }

    @Override
    public Observable<ApiPopularMoviesResponse> popularStream(@Query("api_key") String apiKey) {
        ApiPopularMoviesResponse moviesResponse = new PublicApiPopularMoviesResponse(popularStream);
        return Observable.just(moviesResponse);
    }

    @Override
    public Observable<ApiMovieDetails> movieDetails(@Path("id") long movieId, @Query("api_key") String apiKey) {
        return Observable.just(movieDetails.get(movieId));
    }
}
