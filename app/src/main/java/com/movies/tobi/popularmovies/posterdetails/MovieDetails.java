package com.movies.tobi.popularmovies.posterdetails;

public class MovieDetails {

    public final long movieId;

    public final String posterPath;

    public final String originalTitle;

    public final String overview;

    public final String release_date;

    private MovieDetails(long movieId, String posterPath, String originalTitle, String overview, String release_date) {
        this.movieId = movieId;
        this.posterPath = posterPath;
        this.originalTitle = originalTitle;
        this.overview = overview;
        this.release_date = release_date;
    }

    public static class MovieDetailsBuilder {
        private long movieId;
        private String posterPath;
        private String originalTitle;
        private String overview;
        private String releaseDate;

        public MovieDetailsBuilder setMovieId(long movieId) {
            this.movieId = movieId;
            return this;
        }

        public MovieDetailsBuilder setPosterPath(String posterPath) {
            this.posterPath = posterPath;
            return this;
        }

        public MovieDetailsBuilder setOriginalTitle(String originalTitle) {
            this.originalTitle = originalTitle;
            return this;
        }

        public MovieDetailsBuilder setOverview(String overview) {
            this.overview = overview;
            return this;
        }

        public MovieDetailsBuilder setReleaseDate(String release_date) {
            this.releaseDate = release_date;
            return this;
        }

        public MovieDetails createMovieDetails() {
            return new MovieDetails(movieId, posterPath, originalTitle, overview, releaseDate);
        }
    }
}
