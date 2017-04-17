package com.tobi.movies;

import android.app.Application;
import android.support.annotation.VisibleForTesting;

import com.tobi.movies.backend.Backend;
import com.tobi.movies.popularstream.DaggerPopularMoviesComponent;
import com.tobi.movies.popularstream.PopularMoviesComponent;
import com.tobi.movies.popularstream.PopularStreamRepository;
import com.tobi.movies.posterdetails.DaggerMovieDetailsComponent;
import com.tobi.movies.posterdetails.MovieDetailsComponent;
import com.tobi.movies.posterdetails.MovieDetailsRepository;

import rx.Scheduler;

public class MovieApplication extends Application implements Dependencies {

    private Dependencies dependencies;

    private ApplicationComponent applicationComponent;
    private PopularMoviesComponent popularMoviesComponent;
    private MovieDetailsComponent movieDetailsComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        dependencies = new ApplicationDependencies();
        applicationComponent = applicationComponent();
        popularMoviesComponent = popularMoviesComponent(applicationComponent);
        movieDetailsComponent = movieDetailsComponent(applicationComponent);
    }

    protected ApplicationComponent applicationComponent() {
        return DaggerApplicationComponent.create();
    }

    protected MovieDetailsComponent movieDetailsComponent(ApplicationComponent applicationComponent) {
        return DaggerMovieDetailsComponent.builder()
                .applicationComponent(applicationComponent)
                .build();
    }

    protected PopularMoviesComponent popularMoviesComponent(ApplicationComponent applicationComponent) {
        return DaggerPopularMoviesComponent.builder()
                .applicationComponent(applicationComponent)
                .build();
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
