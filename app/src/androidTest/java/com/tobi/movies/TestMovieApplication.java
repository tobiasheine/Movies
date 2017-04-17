package com.tobi.movies;

import com.tobi.movies.popularstream.DaggerTestPopularMoviesComponent;
import com.tobi.movies.popularstream.PopularMoviesComponent;
import com.tobi.movies.posterdetails.DaggerTestMovieDetailsComponent;
import com.tobi.movies.posterdetails.MovieDetailsComponent;

public class TestMovieApplication extends MovieApplication {

    @Override
    protected ApplicationComponent applicationComponent() {
        return DaggerTestApplicationComponent.create();
    }

    @Override
    protected PopularMoviesComponent popularMoviesComponent(ApplicationComponent applicationComponent) {
        return DaggerTestPopularMoviesComponent.builder()
                .testApplicationComponent((TestApplicationComponent) applicationComponent)
                .build();
    }

    @Override
    protected MovieDetailsComponent movieDetailsComponent(ApplicationComponent applicationComponent) {
        return DaggerTestMovieDetailsComponent.builder()
                .testApplicationComponent((TestApplicationComponent) applicationComponent)
                .build();
    }
}
