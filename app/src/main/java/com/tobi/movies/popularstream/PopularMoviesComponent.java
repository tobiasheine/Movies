package com.tobi.movies.popularstream;

import com.tobi.movies.ApplicationComponent;
import com.tobi.movies.di.ActivityScope;
import com.tobi.movies.misc.ImageModule;
import com.tobi.movies.misc.ThreadingModule;

import dagger.Component;

@ActivityScope
@Component(
        modules = {
                ImageModule.class,
                StreamApiModule.class,
                StreamConverterModule.class,
                ThreadingModule.class
        },
        dependencies = ApplicationComponent.class
)
public interface PopularMoviesComponent {

    void inject(PopularMoviesActivity popularMoviesActivity);
}
