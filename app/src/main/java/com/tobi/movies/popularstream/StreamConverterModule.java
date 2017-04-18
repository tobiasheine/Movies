package com.tobi.movies.popularstream;

import com.tobi.movies.Converter;

import dagger.Module;
import dagger.Provides;

@Module
class StreamConverterModule {

    @Provides
    Converter<ApiMoviePoster, MoviePoster> providePosterConverter() {
        return new ApiMoviePosterConverter();
    }
}
