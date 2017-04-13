package com.tobi.movies.popularstream;

import com.tobi.movies.backend.FakeBackendModule;

import dagger.Component;

@Component(
        modules = {
                FakeBackendModule.class
        }
)
public interface TestPopularMoviesComponent extends PopularMoviesComponent {
    void inject(PopularMoviesActivityTest popularMoviesActivityTest);
}
