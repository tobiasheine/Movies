package com.tobi.movies;

import com.tobi.movies.backend.Backend;
import com.tobi.movies.backend.BackendModule;

import dagger.Component;

@Component(modules = BackendModule.class)
public interface ApplicationComponent {
    Backend backend();
}
