package com.tobi.movies.posterdetails;

import android.support.annotation.NonNull;

import com.tobi.movies.Converter;

import javax.inject.Inject;

import rx.Observable;
import rx.functions.Func1;

public class MovieDetailsRepository {

    private final MovieDetailsApiDatasource apiDatasource;
    private final Converter<ApiMovieDetails, MovieDetails> converter;

    @Inject
    public MovieDetailsRepository(MovieDetailsApiDatasource apiDatasource,
                                  Converter<ApiMovieDetails, MovieDetails> converter) {
        this.apiDatasource = apiDatasource;
        this.converter = converter;
    }

    public Observable<MovieDetails> getMovieDetails(long movieId) {
        return apiDatasource.getMovieDetails(movieId).
                map(toMovieDetails());
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
