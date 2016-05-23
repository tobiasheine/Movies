package com.movies.tobi.popularmovies;

import android.os.AsyncTask;

import com.movies.tobi.popularmovies.backend.Backend;
import com.movies.tobi.popularmovies.backend.ConfigurableBackend;
import com.movies.tobi.popularmovies.popularstream.ApiMoviePoster;
import com.movies.tobi.popularmovies.popularstream.ApiMoviePosterAssetConverter;
import com.movies.tobi.popularmovies.popularstream.MoviePoster;
import com.movies.tobi.popularmovies.posterdetails.ApiMovieDetails;
import com.movies.tobi.popularmovies.posterdetails.ApiMovieDetailsAssetConverter;
import com.movies.tobi.popularmovies.posterdetails.MovieDetails;

import rx.Scheduler;
import rx.schedulers.Schedulers;

public class EspressoDependencies extends ApplicationDependencies {

    private final ConfigurableBackend backend;

    public EspressoDependencies(ConfigurableBackend backend) {
        this.backend = backend;
    }

    @Override
    protected Converter<ApiMovieDetails, MovieDetails> createMovieDetailsConverter() {
        return new ApiMovieDetailsAssetConverter();
    }

    @Override
    protected Converter<ApiMoviePoster, MoviePoster> createPosterConverter() {
        return new ApiMoviePosterAssetConverter();
    }

    @Override
    protected Backend createBackend() {
        return backend;
    }

    @Override
    protected Scheduler createSubscribeScheduler() {
        // IdlingResource is not needed since espresso waits for AsyncTask.THREAD_POOL_EXECUTOR
        return Schedulers.from(AsyncTask.THREAD_POOL_EXECUTOR);
    }
}
