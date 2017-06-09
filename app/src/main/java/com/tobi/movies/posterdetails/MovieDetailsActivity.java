package com.tobi.movies.posterdetails;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.tobi.movies.ImageLoader;
import com.tobi.movies.MovieApplication;
import com.tobi.movies.R;
import com.tobi.movies.misc.Threading;

import javax.inject.Inject;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Scheduler;

public class MovieDetailsActivity extends Activity implements MovieDetailsMVP.View {

    private static final String EXTRA_MOVIE_ID = "extra_movie_id";
    private MovieDetailsUseCase movieDetailsUseCase;

    public static Intent createIntentFor(long movieId, Context activity) {
        Intent intent = new Intent(activity, MovieDetailsActivity.class);
        intent.putExtra(EXTRA_MOVIE_ID, movieId);

        return intent;
    }

    @BindView(R.id.movieTitle)
    TextView movieTitle;

    @BindView(R.id.posterImage)
    ImageView moviePoster;

    @BindView(R.id.movieOverview)
    TextView movieOverview;

    @Inject
    ImageLoader imageLoader;

    @Inject
    Map<Threading, Scheduler> schedulerMap;

    @Inject
    MovieDetailsRepository movieDetailsRepository;

    private MovieDetailsPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        ButterKnife.bind(this);
        MovieApplication application = (MovieApplication) getApplication();
        application.getMovieDetailsComponent().inject(this);

        movieDetailsUseCase = provideMovieDetailsUseCase();
        presenter = new MovieDetailsPresenter(movieDetailsUseCase);
    }

    private MovieDetailsUseCase provideMovieDetailsUseCase() {
        Object lastNonConfigurationInstance = getLastNonConfigurationInstance();
        if (lastNonConfigurationInstance == null) {
            return new MovieDetailsUseCase(movieDetailsRepository, schedulerMap.get(Threading.SUBSCRIBER), schedulerMap.get(Threading.OBSERVER));
        }
        return ((MovieDetailsUseCase) lastNonConfigurationInstance);
    }

    @Override
    public Object onRetainNonConfigurationInstance() {
        return movieDetailsUseCase;
    }

    @Override
    public void display(MovieDetails movieDetails) {
        movieTitle.setText(movieDetails.getOriginalTitle());
        movieOverview.setText(movieDetails.getOverview());
        imageLoader.loadWebImageInto(Uri.parse(movieDetails.getPosterPath()), moviePoster);
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.startPresenting(this, getMovieId());
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
