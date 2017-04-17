package com.tobi.movies.popularstream;

import com.tobi.movies.Converter;

import dagger.Module;
import dagger.Provides;

@Module
class TestStreamConverterModule {

    @Provides
    Converter<ApiMoviePoster, MoviePoster> provideStreamConverter() {
        return new ApiMoviePosterAssetConverter();
    }
}
