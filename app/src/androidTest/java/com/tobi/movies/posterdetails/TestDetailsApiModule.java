package com.tobi.movies.posterdetails;

import com.tobi.movies.backend.Backend;
import com.tobi.movies.backend.FakeBackendModule;

import dagger.Module;
import dagger.Provides;

@Module(includes = FakeBackendModule.class)
class TestDetailsApiModule {

    @Provides
    MovieDetailsApiDatasource provideDetailsApiSource(Backend backend) {
        return new MovieDetailsApiDatasource(backend);
    }
}
