package com.tobi.movies.popularstream;

import com.tobi.movies.backend.BackendModule;
import com.tobi.movies.misc.ImageModule;

import dagger.Component;

@Component(
        modules = {
                BackendModule.class,
                ImageModule.class
        }
)
public interface PopularMoviesComponent {

    void inject(PopularMoviesActivity popularMoviesActivity);
}
