package com.movies.tobi.popularmovies;

import com.movies.tobi.popularmovies.backend.Backend;
import com.movies.tobi.popularmovies.popularstream.ApiMoviePoster;
import com.movies.tobi.popularmovies.popularstream.ApiMoviePosterConverter;
import com.movies.tobi.popularmovies.popularstream.MoviePoster;
import com.movies.tobi.popularmovies.popularstream.PopularStreamApiDatasource;
import com.movies.tobi.popularmovies.popularstream.PopularStreamRepository;
import com.movies.tobi.popularmovies.posterdetails.ApiMovieDetails;
import com.movies.tobi.popularmovies.posterdetails.ApiMovieDetailsConverter;
import com.movies.tobi.popularmovies.posterdetails.MovieDetails;
import com.movies.tobi.popularmovies.posterdetails.MovieDetailsApiDatasource;
import com.movies.tobi.popularmovies.posterdetails.MovieDetailsPresenter;
import com.movies.tobi.popularmovies.posterdetails.MovieDetailsRepository;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ApplicationDependencies implements Dependencies {

    private ImageLoader imageLoader;
    private Backend backend;
    private PopularStreamRepository streamRepository;
    private MovieDetailsPresenter movieDetailsPresenter;

    @Override
    public ImageLoader imageLoader() {
        if (imageLoader == null) {
            imageLoader = createImageLoader();
        }

        return imageLoader;
    }

    @Override
    public PopularStreamRepository streamRepository() {
        if (streamRepository == null) {
            streamRepository = createStreamRepository();
        }

        return streamRepository;
    }

    @Override
    public MovieDetailsPresenter movieDetailsPresenter() {
        if (movieDetailsPresenter == null) {
            movieDetailsPresenter = createMovieDetailsPresenter();
        }

        return movieDetailsPresenter;
    }

    protected MovieDetailsPresenter createMovieDetailsPresenter() {
        return new MovieDetailsPresenter(createMovieDetailsRepository());
    }

    protected ImageLoader createImageLoader() {
        return new ImageLoader();
    }

    protected Converter<ApiMoviePoster, MoviePoster> createPosterConverter() {
        return new ApiMoviePosterConverter();
    }

    protected PopularStreamApiDatasource createStreamApiDataSource() {
        return new PopularStreamApiDatasource(createBackend());
    }

    protected Scheduler createSubscribeScheduler() {
        return Schedulers.io();
    }

    protected Scheduler createObserveScheduler() {
        return AndroidSchedulers.mainThread();
    }

    protected Backend createBackend() {

        return new Retrofit.Builder()
                .baseUrl(Backend.SERVICE_ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build().create(Backend.class);
    }

    private PopularStreamRepository createStreamRepository() {
        return new PopularStreamRepository(
                createStreamApiDataSource(),
                createSubscribeScheduler(),
                createObserveScheduler(),
                createPosterConverter()
        );
    }

    private MovieDetailsRepository createMovieDetailsRepository() {
        return new MovieDetailsRepository(
                createMovieDetailsApiSource(),
                createMovieDetailsConverter(),
                createSubscribeScheduler(),
                createObserveScheduler());
    }

    protected Converter<ApiMovieDetails, MovieDetails> createMovieDetailsConverter() {
        return new ApiMovieDetailsConverter();
    }

    protected MovieDetailsApiDatasource createMovieDetailsApiSource() {
        return new MovieDetailsApiDatasource(backend());
    }

    private Backend backend() {
        if (backend == null) {
            backend = createBackend();
        }

        return backend;
    }

}
