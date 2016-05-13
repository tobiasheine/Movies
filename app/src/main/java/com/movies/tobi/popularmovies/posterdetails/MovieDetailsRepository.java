package com.movies.tobi.popularmovies.posterdetails;

import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;

import com.movies.tobi.popularmovies.Converter;

import rx.Observable;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class MovieDetailsRepository {

    private final MovieDetailsApiDatasource apiDatasource;
    private final Converter<ApiMovieDetails, MovieDetails> converter;
    private final Scheduler subscribeScheduler;
    private final Scheduler observeScheduler;

    @VisibleForTesting
    MovieDetailsRepository(MovieDetailsApiDatasource apiDatasource,
                           Converter<ApiMovieDetails, MovieDetails> converter,
                           Scheduler subscribeScheduler,
                           Scheduler observeScheduler) {
        this.apiDatasource = apiDatasource;
        this.converter = converter;
        this.subscribeScheduler = subscribeScheduler;
        this.observeScheduler = observeScheduler;
    }

    public MovieDetailsRepository() {
        this(
                new MovieDetailsApiDatasource(),
                new ApiMovieDetailsConverter(),
                Schedulers.io(),
                AndroidSchedulers.mainThread()
        );
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
