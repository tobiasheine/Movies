package com.tobi.movies.popularstream;

import com.tobi.movies.backend.Backend;

import dagger.Module;
import dagger.Provides;

@Module()
class StreamApiModule {

    @Provides
    PopularStreamApiDatasource provideStreamApiSource(Backend backend) {
        return new PopularStreamApiDatasource(backend);
    }
}
