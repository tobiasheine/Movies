package com.tobi.movies.misc;

import com.tobi.movies.ImageLoader;

import dagger.Module;
import dagger.Provides;

@Module
public class ImageModule {

    @Provides
    ImageLoader provideImageLoader() {
        return new ImageLoader();
    }
}
