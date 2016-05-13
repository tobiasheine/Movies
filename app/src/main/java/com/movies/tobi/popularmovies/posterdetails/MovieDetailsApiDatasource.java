package com.movies.tobi.popularmovies.posterdetails;

import android.support.annotation.VisibleForTesting;

import com.movies.tobi.popularmovies.BuildConfig;
import com.movies.tobi.popularmovies.backend.Backend;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

public class MovieDetailsApiDatasource {

    private final Backend backend;

    @VisibleForTesting
    MovieDetailsApiDatasource(Backend backend) {
        this.backend = backend;
    }

    public MovieDetailsApiDatasource() {
        this(new Retrofit.Builder()
                     .baseUrl(Backend.SERVICE_ENDPOINT)
                     .addConverterFactory(GsonConverterFactory.create())
                     .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                     .build().create(Backend.class));
    }

    public Observable<ApiMovieDetails> getMovieDetails(long movieId) {
        return backend.movieDetails(movieId, BuildConfig.API_KEY);
    }
}
