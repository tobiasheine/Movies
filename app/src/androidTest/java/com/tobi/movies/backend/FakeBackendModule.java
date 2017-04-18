package com.tobi.movies.backend;

import com.tobi.movies.di.ApplicationScope;

import dagger.Module;
import dagger.Provides;

@Module
public class FakeBackendModule {

    @ApplicationScope
    @Provides
    Backend provideBackend() {
        return new ConfigurableBackend();
    }
}
