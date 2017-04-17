package com.tobi.movies.backend;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class FakeBackendModule {

    @Singleton
    @Provides
    Backend provideBackend() {
        return new ConfigurableBackend();
    }
}
