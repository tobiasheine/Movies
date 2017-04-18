package com.tobi.movies.popularstream;

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
                TestStreamConverterModule.class,
                TestStreamApiModule.class,
                TestThreadingModule.class,
        },
        dependencies = TestApplicationComponent.class
)
public interface TestPopularMoviesComponent extends PopularMoviesComponent {
    Backend backend();
}
