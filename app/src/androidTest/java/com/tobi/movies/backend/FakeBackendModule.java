package com.tobi.movies.backend;

import dagger.Module;
import dagger.Provides;

@Module
public class FakeBackendModule {

    @Provides
    Backend provideBackend() {
        return new ConfigurableBackend();
    }
}
