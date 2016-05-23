package com.tobi.movies.posterdetails;

import com.tobi.movies.Converter;

public class ApiMovieDetailsConverter implements Converter<ApiMovieDetails, MovieDetails> {

    private static final String IMAGE_BASE_URL = "http://image.tmdb.org/t/p/w500/";

    @Override
    public MovieDetails convert(ApiMovieDetails input) {
        String imageUrl = IMAGE_BASE_URL + input.posterPath.substring(1, input.posterPath.length());

        return new MovieDetails.MovieDetailsBuilder().
                setMovieId(input.movieId).
                setOriginalTitle(input.originalTitle).
                setOverview(input.overview).
                setPosterPath(imageUrl).
                setReleaseDate(input.releaseDate).
                createMovieDetails();
    }
}
