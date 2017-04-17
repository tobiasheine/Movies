package com.tobi.movies.posterdetails;

import com.tobi.movies.Converter;

import dagger.Module;
import dagger.Provides;

@Module
class DetailsConverterModule {

    @Provides
    Converter<ApiMovieDetails, MovieDetails> provideDetailsConverter() {
        return new ApiMovieDetailsConverter();
    }
}
