package com.tobi.movies.popularstream;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MoviePoster that = (MoviePoster) o;

        return movieId == that.movieId
                && posterPath != null ? posterPath.equals(that.posterPath) : that.posterPath == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (movieId ^ (movieId >>> 32));
        result = 31 * result + (posterPath != null ? posterPath.hashCode() : 0);
        return result;
    }
}
