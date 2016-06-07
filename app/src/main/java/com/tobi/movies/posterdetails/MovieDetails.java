package com.tobi.movies.posterdetails;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class MovieDetails implements MovieDetailsMVP.Model {

    @Override
    public abstract String overview();

    @Override
    public abstract long movieId();

    @Override
    public abstract String originalTitle();

    @Override
    public abstract String posterPath();

    @Override
    public abstract String releaseDate();

    static Builder builder() {
        return new AutoValue_MovieDetails.Builder();
    }

    @AutoValue.Builder
    abstract static class Builder {
        abstract Builder overview(String overview);

        abstract Builder movieId(long movieId);

        abstract Builder originalTitle(String originalTitle);

        abstract Builder posterPath(String posterPath);

        abstract Builder releaseDate(String releaseDate);

        abstract MovieDetails build();
    }
}
