package com.movies.tobi.popularmovies.posterdetails;

import android.support.annotation.NonNull;

import com.movies.tobi.popularmovies.Converter;

import rx.Observable;
import rx.Scheduler;
import rx.functions.Func1;

public class MovieDetailsRepository {

    private final MovieDetailsApiDatasource apiDatasource;
    private final Converter<ApiMovieDetails, MovieDetails> converter;
    private final Scheduler subscribeScheduler;
    private final Scheduler observeScheduler;

    public MovieDetailsRepository(MovieDetailsApiDatasource apiDatasource,
                           Converter<ApiMovieDetails, MovieDetails> converter,
                           Scheduler subscribeScheduler,
                           Scheduler observeScheduler) {
        this.apiDatasource = apiDatasource;
        this.converter = converter;
        this.subscribeScheduler = subscribeScheduler;
        this.observeScheduler = observeScheduler;
    }

    public Observable<MovieDetails> getMovieDetails(long movieId) {
        return apiDatasource.getMovieDetails(movieId).
                map(toMovieDetails()).
                subscribeOn(subscribeScheduler).
                observeOn(observeScheduler);
    }

    @NonNull
    private Func1<ApiMovieDetails, MovieDetails> toMovieDetails() {
        return new Func1<ApiMovieDetails, MovieDetails>() {
            @Override
            public MovieDetails call(ApiMovieDetails apiMovieDetails) {
                return converter.convert(apiMovieDetails);
            }
        };
    }
}
