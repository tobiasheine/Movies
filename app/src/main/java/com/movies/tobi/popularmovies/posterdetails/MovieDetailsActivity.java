package com.movies.tobi.popularmovies.posterdetails;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.movies.tobi.popularmovies.ImageLoader;
import com.movies.tobi.popularmovies.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MovieDetailsActivity extends Activity implements MovieDetailsMVP.View {

    private static final String EXTRA_MOVIE_ID = "extra_movie_id";

    public static Intent createIntentFor(long movieId, Context activity) {
        Intent intent = new Intent(activity, MovieDetailsActivity.class);
        intent.putExtra(EXTRA_MOVIE_ID, movieId);

        return intent;
    }

    @Bind(R.id.movieTitle)
    TextView movieTitle;

    @Bind(R.id.posterImage)
    ImageView moviePoster;

    @Bind(R.id.movieOverview)
    TextView movieOverview;

    private MovieDetailsPresenter presenter;
    private ImageLoader imageLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        ButterKnife.bind(this);

        imageLoader = new ImageLoader();
        presenter = new MovieDetailsPresenter(this, getMovieId());
    }

    @Override
    public void display(MovieDetailsMVP.Model model) {
        movieTitle.setText(model.getOriginalTitle());
        movieOverview.setText(model.getOverview());
        imageLoader.loadWebImageInto(Uri.parse(model.getPosterPath()), moviePoster);
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
