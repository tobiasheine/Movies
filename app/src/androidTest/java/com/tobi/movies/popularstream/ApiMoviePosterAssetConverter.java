package com.tobi.movies.popularstream;

import com.tobi.movies.Converter;

public class ApiMoviePosterAssetConverter implements Converter<ApiMoviePoster, MoviePoster> {

    private final static String ASSET_PATH = "file:///android_asset/";

    @Override
    public MoviePoster convert(ApiMoviePoster input) {
        String imageUrl = ASSET_PATH + input.getPosterPath().substring(0, input.getPosterPath().length());
        return new MoviePoster(input.getMovieId(), imageUrl);
    }
}
