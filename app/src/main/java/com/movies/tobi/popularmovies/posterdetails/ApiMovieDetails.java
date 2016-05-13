package com.movies.tobi.popularmovies.posterdetails;

import com.google.gson.annotations.SerializedName;

public class ApiMovieDetails {

    @SerializedName("id")
    long movieId;

    @SerializedName("poster_path")
    String posterPath;

    @SerializedName("original_title")
    String originalTitle;

    @SerializedName("overview")
    String overview;

    @SerializedName("release_date")
    String releaseDate;
}
