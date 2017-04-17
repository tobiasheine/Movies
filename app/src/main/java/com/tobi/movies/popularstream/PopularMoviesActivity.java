package com.tobi.movies.popularstream;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.tobi.movies.ImageLoader;
import com.tobi.movies.MovieApplication;
import com.tobi.movies.R;
import com.tobi.movies.backend.Backend;
import com.tobi.movies.misc.AbstractObserver;

import javax.inject.Inject;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PopularMoviesActivity extends AppCompatActivity {

    private static final String TAG = PopularMoviesActivity.class.getSimpleName();
    private static final int POSTER_COL_COUNT = 3;

    @Bind(R.id.popularMovies_recycler)
    RecyclerView popularMoviesRecycler;

    @Inject
    Backend backend;

    @Inject
    ImageLoader imageLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popular_movies);
        ButterKnife.bind(this);
        final MovieApplication movieApplication = (MovieApplication) getApplicationContext();
        movieApplication.getPopularMoviesComponent().inject(this);
        movieApplication.setBackend(backend);

        popularMoviesRecycler.setLayoutManager(new GridLayoutManager(this, POSTER_COL_COUNT));
        popularMoviesRecycler.setHasFixedSize(true);

        final Navigator navigator = new Navigator(this);

        movieApplication.streamRepository().getPopularPosters().subscribe(new AbstractObserver<List<MoviePoster>>() {

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "Error loading movie posters", e);
            }

            @Override
            public void onNext(List<MoviePoster> moviePosters) {
                popularMoviesRecycler.setAdapter(new MoviePosterAdapter(moviePosters, imageLoader, navigator));
            }
        });

    }
}
