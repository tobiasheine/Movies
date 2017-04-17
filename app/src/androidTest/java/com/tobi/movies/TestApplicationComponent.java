package com.tobi.movies;

import com.tobi.movies.backend.Backend;
import com.tobi.movies.backend.FakeBackendModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = FakeBackendModule.class)
public interface TestApplicationComponent extends ApplicationComponent{
    Backend backend();
}
