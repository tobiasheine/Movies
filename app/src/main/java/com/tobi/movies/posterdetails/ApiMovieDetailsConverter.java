package com.tobi.movies.posterdetails;

import com.tobi.movies.Converter;

public class ApiMovieDetailsConverter implements Converter<ApiMovieDetails, MovieDetails> {

    private static final String IMAGE_BASE_URL = "http://image.tmdb.org/t/p/w500/";

    @Override
    public MovieDetails convert(ApiMovieDetails input) {
        String imageUrl = IMAGE_BASE_URL + input.posterPath.substring(1, input.posterPath.length());

        return MovieDetails.builder().
                movieId(input.movieId).
                originalTitle(input.originalTitle).
                overview(input.overview).
                posterPath(imageUrl).
                releaseDate(input.releaseDate).
                build();
    }
}
