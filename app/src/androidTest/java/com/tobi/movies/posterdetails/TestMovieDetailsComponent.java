package com.tobi.movies.posterdetails;

import com.tobi.movies.TestApplicationComponent;
import com.tobi.movies.backend.Backend;
import com.tobi.movies.di.ActivityScope;
import com.tobi.movies.misc.ImageModule;
import com.tobi.movies.misc.TestThreadingModule;

import dagger.Component;

@ActivityScope
@Component(
        modules = {
                ImageModule.class,
                TestDetailsApiModule.class,
                TestThreadingModule.class,
                TestDetailsConverterModule.class
        },
        dependencies = TestApplicationComponent.class
)
interface TestMovieDetailsComponent extends MovieDetailsComponent {

    Backend backend();

//    void inject(MovieDetailsActivityTest movieDetailsActivityTest);
}
