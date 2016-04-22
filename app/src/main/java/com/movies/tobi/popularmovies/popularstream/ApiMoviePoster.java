package com.movies.tobi.popularmovies.popularstream;

import com.google.gson.annotations.SerializedName;

public class ApiMoviePoster {

    @SerializedName("poster_path")
    String posterPath;

    @SerializedName("id")
    long movieId;

}
