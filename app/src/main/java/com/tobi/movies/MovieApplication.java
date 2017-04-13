package com.tobi.movies;

import android.app.Application;
import android.support.annotation.VisibleForTesting;

import com.tobi.movies.backend.Backend;
import com.tobi.movies.backend.BackendModule;
import com.tobi.movies.popularstream.DaggerPopularMoviesComponent;
import com.tobi.movies.popularstream.PopularMoviesComponent;
import com.tobi.movies.popularstream.PopularStreamRepository;
import com.tobi.movies.posterdetails.DaggerMovieDetailsComponent;
import com.tobi.movies.posterdetails.MovieDetailsComponent;
import com.tobi.movies.posterdetails.MovieDetailsRepository;

import rx.Scheduler;

public class MovieApplication extends Application implements Dependencies {

    private Dependencies dependencies;

    private PopularMoviesComponent popularMoviesComponent;
    private MovieDetailsComponent movieDetailsComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        dependencies = new ApplicationDependencies();
        popularMoviesComponent = popularMoviesComponent();

        movieDetailsComponent = movieDetailsComponent();
    }

    protected MovieDetailsComponent movieDetailsComponent() {
        return DaggerMovieDetailsComponent.builder()
                .backendModule(new BackendModule())
                .build();
    }

    protected PopularMoviesComponent popularMoviesComponent() {
        return DaggerPopularMoviesComponent.builder()
                .backendModule(new BackendModule())
                .build();
    }

    @Override
    public ImageLoader imageLoader() {
        return dependencies.imageLoader();
    }

    @Override
    public PopularStreamRepository streamRepository() {
        return dependencies.streamRepository();
    }

    @Override
    public MovieDetailsRepository movieDetailsRepository() {
        return dependencies.movieDetailsRepository();
    }

    @Override
    public Scheduler createSubscriberThread() {
        return dependencies.createSubscriberThread();
    }

    @Override
    public Scheduler createObserverThread() {
        return dependencies.createObserverThread();
    }

    @Override
    public void setBackend(Backend backend) {
        dependencies.setBackend(backend);
    }

    @VisibleForTesting
    public void setDependencies(Dependencies dependencies) {
        this.dependencies = dependencies;
    }

    @VisibleForTesting
    public Dependencies getDependencies() {
        return dependencies;
    }

    public PopularMoviesComponent getPopularMoviesComponent() {
        return popularMoviesComponent;
    }

    public MovieDetailsComponent getMovieDetailsComponent() {
        return movieDetailsComponent;
    }
}
