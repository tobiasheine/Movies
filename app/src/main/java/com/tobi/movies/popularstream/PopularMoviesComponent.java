package com.tobi.movies.popularstream;

import com.tobi.movies.backend.BackendModule;

import dagger.Component;

@Component(
        modules = {
                BackendModule.class
        }
)
public interface PopularMoviesComponent {

    void inject(PopularMoviesActivity popularMoviesActivity);
}
