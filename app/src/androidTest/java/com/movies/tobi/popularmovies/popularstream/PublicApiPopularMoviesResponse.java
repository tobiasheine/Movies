package com.movies.tobi.popularmovies.popularstream;

import java.util.List;

public class PublicApiPopularMoviesResponse extends ApiPopularMoviesResponse {

    public PublicApiPopularMoviesResponse(List<ApiMoviePoster> results) {
        this.results = results;
    }

}
