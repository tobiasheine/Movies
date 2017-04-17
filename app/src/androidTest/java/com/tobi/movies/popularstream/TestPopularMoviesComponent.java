package com.tobi.movies.popularstream;

import com.tobi.movies.backend.FakeBackendModule;
import com.tobi.movies.misc.ImageModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(
        modules = {
                FakeBackendModule.class,
                ImageModule.class
        }
)
interface TestPopularMoviesComponent extends PopularMoviesComponent {
    void inject(PopularMoviesActivityTest popularMoviesActivityTest);
}
