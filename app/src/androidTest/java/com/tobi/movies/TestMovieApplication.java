package com.tobi.movies;

import com.tobi.movies.popularstream.DaggerTestPopularMoviesComponent;
import com.tobi.movies.popularstream.PopularMoviesComponent;

public class TestMovieApplication extends MovieApplication {

    @Override
    protected PopularMoviesComponent popularMoviesComponent() {
        return DaggerTestPopularMoviesComponent.builder().build();
    }
}
