package com.movies.tobi.popularmovies;

import android.app.Application;
import android.support.annotation.VisibleForTesting;

import com.movies.tobi.popularmovies.popularstream.PopularStreamRepository;
import com.movies.tobi.popularmovies.posterdetails.MovieDetailsPresenter;

public class MovieApplication extends Application implements Dependencies {

    private Dependencies dependencies;

    @Override
    public void onCreate() {
        super.onCreate();
        dependencies = new ApplicationDependencies();
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
    public MovieDetailsPresenter movieDetailsPresenter() {
        return dependencies.movieDetailsPresenter();
    }

    @VisibleForTesting
    public void setDependencies(Dependencies dependencies) {
        this.dependencies = dependencies;
    }
}
