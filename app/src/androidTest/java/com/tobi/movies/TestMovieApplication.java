package com.tobi.movies;

import com.tobi.movies.popularstream.DaggerTestPopularMoviesComponent;
import com.tobi.movies.popularstream.PopularMoviesComponent;
import com.tobi.movies.posterdetails.DaggerTestMovieDetailsComponent;
import com.tobi.movies.posterdetails.MovieDetailsComponent;

public class TestMovieApplication extends MovieApplication {

    @Override
    protected PopularMoviesComponent popularMoviesComponent() {
        return DaggerTestPopularMoviesComponent.builder().build();
    }

    @Override
    protected MovieDetailsComponent movieDetailsComponent() {
        return DaggerTestMovieDetailsComponent.builder().build();
    }
}
