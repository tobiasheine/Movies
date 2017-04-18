package com.tobi.movies.posterdetails;

import com.tobi.movies.backend.Backend;

import dagger.Module;
import dagger.Provides;

@Module()
class TestDetailsApiModule {

    @Provides
    MovieDetailsApiDatasource provideDetailsApiSource(Backend backend) {
        return new MovieDetailsApiDatasource(backend);
    }
}
