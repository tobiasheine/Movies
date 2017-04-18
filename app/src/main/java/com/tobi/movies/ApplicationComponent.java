package com.tobi.movies;

import com.tobi.movies.backend.Backend;
import com.tobi.movies.backend.BackendModule;
import com.tobi.movies.di.ApplicationScope;

import dagger.Component;

@ApplicationScope
@Component(modules = BackendModule.class)
public interface ApplicationComponent {
    Backend backend();
}
