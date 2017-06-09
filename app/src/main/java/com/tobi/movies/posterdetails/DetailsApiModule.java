package com.tobi.movies.posterdetails;

import com.tobi.movies.Converter;
import com.tobi.movies.backend.Backend;

import dagger.Module;
import dagger.Provides;

@Module()
class DetailsApiModule {

    @Provides
    MovieDetailsApiDatasource provideDetailsApiSource(Backend backend) {
        return new MovieDetailsApiDatasource(backend);
    }

    @Provides
    MovieDetailsRepository providesDetailsRepository(MovieDetailsApiDatasource apiDatasource,
                                                     Converter<ApiMovieDetails, MovieDetails> converter) {
        return new MovieDetailsRepository(apiDatasource, converter);
    }
}
