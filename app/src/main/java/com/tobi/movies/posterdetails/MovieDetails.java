package com.tobi.movies.posterdetails;

public class MovieDetails implements MovieDetailsMVP.Model {

    private final long movieId;

    private final String posterPath;

    private final String originalTitle;

    private final String overview;

    private final String release_date;

    private MovieDetails(long movieId, String posterPath, String originalTitle, String overview, String release_date) {
        this.movieId = movieId;
        this.posterPath = posterPath;
        this.originalTitle = originalTitle;
        this.overview = overview;
        this.release_date = release_date;
    }

    @Override
    public String getOverview() {
        return overview;
    }

    @Override
    public long getMovieId() {
        return movieId;
    }

    @Override
    public String getOriginalTitle() {
        return originalTitle;
    }

    @Override
    public String getPosterPath() {
        return posterPath;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MovieDetails that = (MovieDetails) o;

        return movieId == that.movieId
                && posterPath != null ? posterPath.equals(that.posterPath) : that.posterPath == null
                && originalTitle != null ? originalTitle.equals(that.originalTitle) : that.originalTitle == null
                && overview != null ? overview.equals(that.overview) : that.overview == null
                && release_date != null ? release_date.equals(that.release_date) : that.release_date == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (movieId ^ (movieId >>> 32));
        result = 31 * result + (posterPath != null ? posterPath.hashCode() : 0);
        result = 31 * result + (originalTitle != null ? originalTitle.hashCode() : 0);
        result = 31 * result + (overview != null ? overview.hashCode() : 0);
        result = 31 * result + (release_date != null ? release_date.hashCode() : 0);
        return result;
    }
}
