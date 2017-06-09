package com.tobi.movies.popularstream;

import com.tobi.movies.Converter;

public class ApiMoviePosterConverter implements Converter<ApiMoviePoster, MoviePoster> {

    private static final String IMAGE_BASE_URL = "http://image.tmdb.org/t/p/w500/";

    @Override
    public MoviePoster convert(ApiMoviePoster input) {
        String imageUrl = IMAGE_BASE_URL + input.getPosterPath().substring(1, input.getPosterPath().length());
        return new MoviePoster(input.getMovieId(), imageUrl);
    }
}
