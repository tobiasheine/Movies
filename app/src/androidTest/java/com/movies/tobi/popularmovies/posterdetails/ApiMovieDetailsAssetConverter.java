package com.movies.tobi.popularmovies.posterdetails;

import com.movies.tobi.popularmovies.Converter;

public class ApiMovieDetailsAssetConverter implements Converter<ApiMovieDetails, MovieDetails> {

    private final static String ASSET_PATH = "file:///android_asset/";

    @Override
    public MovieDetails convert(ApiMovieDetails input) {
        String imageUrl = ASSET_PATH + input.posterPath.substring(1, input.posterPath.length());

        return new MovieDetails.MovieDetailsBuilder().
                setMovieId(input.movieId).
                setOriginalTitle(input.originalTitle).
                setOverview(input.overview).
                setPosterPath(imageUrl).
                setReleaseDate(input.releaseDate).
                createMovieDetails();
    }
}
