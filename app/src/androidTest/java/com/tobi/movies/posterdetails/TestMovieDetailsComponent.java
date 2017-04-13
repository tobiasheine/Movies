package com.tobi.movies.posterdetails;

import com.tobi.movies.backend.FakeBackendModule;

import dagger.Component;

@Component(
        modules = {
                FakeBackendModule.class
        }
)
public interface TestMovieDetailsComponent extends MovieDetailsComponent {
    void inject(MovieDetailsActivityTest movieDetailsActivityTest);
}
