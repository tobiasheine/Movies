package com.tobi.movies;

import com.tobi.movies.backend.Backend;
import com.tobi.movies.backend.FakeBackendModule;
import com.tobi.movies.di.ApplicationScope;

import dagger.Component;

@ApplicationScope
@Component(modules = FakeBackendModule.class)
public interface TestApplicationComponent extends ApplicationComponent{
    Backend backend();
}
