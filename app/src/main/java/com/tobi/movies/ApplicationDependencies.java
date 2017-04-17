package com.tobi.movies;

import com.tobi.movies.backend.Backend;
import com.tobi.movies.popularstream.ApiMoviePoster;
import com.tobi.movies.popularstream.ApiMoviePosterConverter;
import com.tobi.movies.popularstream.MoviePoster;
import com.tobi.movies.popularstream.PopularStreamApiDatasource;
import com.tobi.movies.popularstream.PopularStreamRepository;
import com.tobi.movies.posterdetails.ApiMovieDetails;
import com.tobi.movies.posterdetails.ApiMovieDetailsConverter;
import com.tobi.movies.posterdetails.MovieDetails;
import com.tobi.movies.posterdetails.MovieDetailsApiDatasource;
import com.tobi.movies.posterdetails.MovieDetailsRepository;

import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ApplicationDependencies implements Dependencies {

    private Backend backend;
    private PopularStreamRepository streamRepository;
    private MovieDetailsRepository movieDetailsRepository;

    @Override
    public PopularStreamRepository streamRepository() {
        if (streamRepository == null) {
            streamRepository = createStreamRepository();
        }

        return streamRepository;
    }

    @Override
    public MovieDetailsRepository movieDetailsRepository() {
        if (movieDetailsRepository == null) {
            movieDetailsRepository = createMovieDetailsRepository();
        }
        return movieDetailsRepository;
    }

    @Override
    public Scheduler createSubscriberThread() {
        return Schedulers.io();
    }

    @Override
    public Scheduler createObserverThread() {
        return AndroidSchedulers.mainThread();
    }

    protected MovieDetailsRepository createMovieDetailsRepository() {
        return new MovieDetailsRepository(
                createMovieDetailsApiSource(),
                createMovieDetailsConverter()
        );
    }

    protected Converter<ApiMoviePoster, MoviePoster> createPosterConverter() {
        return new ApiMoviePosterConverter();
    }

    protected PopularStreamApiDatasource createStreamApiDataSource() {
        return new PopularStreamApiDatasource(backend());
    }

    private PopularStreamRepository createStreamRepository() {
        return new PopularStreamRepository(
                createStreamApiDataSource(),
                createSubscriberThread(),
                createObserverThread(),
                createPosterConverter()
        );
    }

    protected Converter<ApiMovieDetails, MovieDetails> createMovieDetailsConverter() {
        return new ApiMovieDetailsConverter();
    }

    protected MovieDetailsApiDatasource createMovieDetailsApiSource() {
        return new MovieDetailsApiDatasource(backend());
    }

    protected Backend backend() {
        if (backend == null) {
            throw new IllegalStateException("Backend should be provided via dagger");
        }

        return backend;
    }

    @Override
    public void setBackend(Backend backend) {
        this.backend = backend;
    }
}
