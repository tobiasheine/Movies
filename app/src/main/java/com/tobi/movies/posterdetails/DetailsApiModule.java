package com.tobi.movies.posterdetails;

import com.tobi.movies.backend.Backend;
import com.tobi.movies.backend.BackendModule;

import dagger.Module;
import dagger.Provides;

@Module(includes = BackendModule.class)
class DetailsApiModule {

    @Provides
    MovieDetailsApiDatasource provideDetailsApiSource(Backend backend) {
        return new MovieDetailsApiDatasource(backend);
    }
}
