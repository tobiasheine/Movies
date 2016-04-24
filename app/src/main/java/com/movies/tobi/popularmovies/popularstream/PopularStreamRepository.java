package com.movies.tobi.popularmovies.popularstream;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class PopularStreamRepository {

    private static final String IMAGE_BASE_URL = "http://image.tmdb.org/t/p/w500/";

    private final PopularStreamApiDatasource popularStreamApiDatasource;
    private final Scheduler scheduler;

    PopularStreamRepository(PopularStreamApiDatasource popularStreamApiDatasource, Scheduler scheduler) {
        this.popularStreamApiDatasource = popularStreamApiDatasource;
        this.scheduler = scheduler;
    }

    public PopularStreamRepository(PopularStreamApiDatasource popularStreamApiDatasource) {
        this(popularStreamApiDatasource, Schedulers.io());
    }

    public Observable<List<MoviePoster>> getPopularPosters() {
        return popularStreamApiDatasource.getPopularPosters()
                .map(toMoviePosters())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(scheduler);
    }

    @NonNull
    private Func1<List<ApiMoviePoster>, List<MoviePoster>> toMoviePosters() {
        return new Func1<List<ApiMoviePoster>, List<MoviePoster>>() {
            @Override
            public List<MoviePoster> call(List<ApiMoviePoster> apiMoviePosters) {
                List<MoviePoster> result = new ArrayList<>(apiMoviePosters.size());

                for (ApiMoviePoster apiMoviePoster : apiMoviePosters) {
                    result.add(toMoviePoster(apiMoviePoster));
                }

                return result;
            }
        };
    }

    private MoviePoster toMoviePoster(ApiMoviePoster apiMoviePoster) {
        String imageUrl = IMAGE_BASE_URL + apiMoviePoster.posterPath.substring(1, apiMoviePoster.posterPath.length());
        return new MoviePoster(apiMoviePoster.movieId, imageUrl);
    }

}
