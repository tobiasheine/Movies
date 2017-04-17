package com.tobi.movies.posterdetails;

import com.tobi.movies.backend.FakeBackendModule;
import com.tobi.movies.misc.ImageModule;
import com.tobi.movies.misc.TestThreadingModule;

import dagger.Component;

@Component(
        modules = {
                FakeBackendModule.class,
                ImageModule.class,
                TestThreadingModule.class
        }
)
interface TestMovieDetailsComponent extends MovieDetailsComponent {
    void inject(MovieDetailsActivityTest movieDetailsActivityTest);
}
