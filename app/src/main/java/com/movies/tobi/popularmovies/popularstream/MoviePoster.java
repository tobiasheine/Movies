package com.movies.tobi.popularmovies.popularstream;

public class MoviePoster {

    private final long movieId;
    private final String posterPath;

    public MoviePoster(long movieId, String posterPath) {
        this.movieId = movieId;
        this.posterPath = posterPath;
    }

    public long getMovieId() {
        return movieId;
    }

    public String getPosterPath() {
        return posterPath;
    }
}
