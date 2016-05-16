package com.movies.tobi.popularmovies.popularstream;

import com.movies.tobi.popularmovies.Converter;

public class ApiMoviePosterAssetConverter implements Converter<ApiMoviePoster, MoviePoster> {

    private final static String ASSET_PATH = "file:///android_asset/";

    @Override
    public MoviePoster convert(ApiMoviePoster input) {
        String imageUrl = ASSET_PATH + input.posterPath.substring(1, input.posterPath.length());
        return new MoviePoster(input.movieId, imageUrl);
    }
}
