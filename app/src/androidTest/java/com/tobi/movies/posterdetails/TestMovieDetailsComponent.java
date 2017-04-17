package com.tobi.movies.posterdetails;

import com.tobi.movies.backend.Backend;
import com.tobi.movies.misc.ImageModule;
import com.tobi.movies.misc.TestThreadingModule;

import javax.inject.Singleton;

import dagger.Component;


@Singleton
@Component(
        modules = {
                ImageModule.class,
                TestDetailsApiModule.class,
                TestThreadingModule.class,
                TestDetailsConverterModule.class
        }
)
interface TestMovieDetailsComponent extends MovieDetailsComponent {

    Backend backend();

//    void inject(MovieDetailsActivityTest movieDetailsActivityTest);
}
