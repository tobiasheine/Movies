package com.tobi.movies.posterdetails;

import com.tobi.movies.backend.FakeBackendModule;
import com.tobi.movies.misc.ImageModule;

import dagger.Component;

@Component(
        modules = {
                FakeBackendModule.class,
                ImageModule.class
        }
)
interface TestMovieDetailsComponent extends MovieDetailsComponent {
    void inject(MovieDetailsActivityTest movieDetailsActivityTest);
}
