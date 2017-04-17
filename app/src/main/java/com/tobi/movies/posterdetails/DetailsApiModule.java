package com.tobi.movies.posterdetails;

import com.tobi.movies.backend.Backend;

import dagger.Module;
import dagger.Provides;

@Module()
class DetailsApiModule {

    @Provides
    MovieDetailsApiDatasource provideDetailsApiSource(Backend backend) {
        return new MovieDetailsApiDatasource(backend);
    }
}
