package com.movies.tobi.popularmovies.popularstream;

import com.movies.tobi.popularmovies.Converter;

public class ApiMoviePosterConverter implements Converter<ApiMoviePoster, MoviePoster> {

    private static final String IMAGE_BASE_URL = "http://image.tmdb.org/t/p/w500/";

    @Override
    public MoviePoster convert(ApiMoviePoster input) {
        String imageUrl = IMAGE_BASE_URL + input.posterPath.substring(1, input.posterPath.length());
        return new MoviePoster(input.movieId, imageUrl);
    }
}
