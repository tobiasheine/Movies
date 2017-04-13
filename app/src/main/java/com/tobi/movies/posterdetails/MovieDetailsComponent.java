package com.tobi.movies.posterdetails;

import com.tobi.movies.backend.BackendModule;

import dagger.Component;

@Component(
        modules = {
                BackendModule.class
        }
)
public interface MovieDetailsComponent {

    void inject(MovieDetailsActivity movieDetailsActivity);
}
