package com.movies.tobi.popularmovies.posterdetails;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class MovieDetailsActivity extends Activity implements MovieDetailsMVP.View {

    private static final String EXTRA_MOVIE_ID = "extra_movie_id";

    public static Intent createIntentFor(long movieId, Context activity) {
        Intent intent = new Intent(activity, MovieDetailsActivity.class);
        intent.putExtra(EXTRA_MOVIE_ID, movieId);

        return intent;
    }

    private MovieDetailsPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter = new MovieDetailsPresenter(this, getMovieId());
    }

    @Override
    public void display(MovieDetailsMVP.Model model) {
        Toast.makeText(this, model.getOverview(), Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.startPresenting();
    }

    @Override
    protected void onStop() {
        presenter.stopPresenting();
        super.onStop();
    }

    @Override
    public void showError() {
        Toast.makeText(this, "Cannot load movie details for id:" + getMovieId(), Toast.LENGTH_LONG).show();
    }

    private long getMovieId() {
        return getIntent().getLongExtra(EXTRA_MOVIE_ID, -1L);
    }
}
