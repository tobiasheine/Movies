package com.movies.tobi.popularmovies.popularstream;

import android.support.annotation.NonNull;

import com.movies.tobi.popularmovies.BuildConfig;
import com.movies.tobi.popularmovies.backend.Backend;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.functions.Func1;

public class PopularStreamApiDatasource {

    private final Backend backend;

    PopularStreamApiDatasource(Backend backend) {
        this.backend = backend;
    }

    public PopularStreamApiDatasource() {
        this(new Retrofit.Builder()
                     .baseUrl(Backend.SERVICE_ENDPOINT)
                     .addConverterFactory(GsonConverterFactory.create())
                     .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                     .build().create(Backend.class));
    }

    public Observable<List<ApiMoviePoster>> getPopularPosters() {
        return backend.popularStream(BuildConfig.API_KEY)
                .map(tApiMoviePosters());
    }

    @NonNull
    private Func1<ApiPopularMoviesResponse, List<ApiMoviePoster>> tApiMoviePosters() {
        return new Func1<ApiPopularMoviesResponse, List<ApiMoviePoster>>() {
            @Override
            public List<ApiMoviePoster> call(ApiPopularMoviesResponse apiPopularMoviesResponse) {
                return apiPopularMoviesResponse.results;
            }
        };
    }

}
