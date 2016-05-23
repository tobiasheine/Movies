package com.tobi.movies;

import android.app.Activity;
import android.content.Intent;

import com.tobi.movies.posterdetails.MovieDetailsActivity;

public class Navigator {

    private final Activity activity;

    public Navigator(Activity activity) {
        this.activity = activity;
    }

    public void toMovieDetails(long movieId) {
        Intent intent = MovieDetailsActivity.createIntentFor(movieId, activity);
        activity.startActivity(intent);
    }
}
