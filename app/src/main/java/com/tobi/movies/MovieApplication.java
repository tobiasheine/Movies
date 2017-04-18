package com.tobi.movies;

import android.app.Application;

import com.tobi.movies.popularstream.DaggerPopularMoviesComponent;
import com.tobi.movies.popularstream.PopularMoviesComponent;
import com.tobi.movies.posterdetails.DaggerMovieDetailsComponent;
import com.tobi.movies.posterdetails.MovieDetailsComponent;

public class MovieApplication extends Application {

    private PopularMoviesComponent popularMoviesComponent;
    private MovieDetailsComponent movieDetailsComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        ApplicationComponent applicationComponent = applicationComponent();
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

    public PopularMoviesComponent getPopularMoviesComponent() {
        return popularMoviesComponent;
    }

    public MovieDetailsComponent getMovieDetailsComponent() {
        return movieDetailsComponent;
    }
}
