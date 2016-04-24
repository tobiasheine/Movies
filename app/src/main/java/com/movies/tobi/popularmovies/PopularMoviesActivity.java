package com.movies.tobi.popularmovies;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.movies.tobi.popularmovies.popularstream.MoviePoster;
import com.movies.tobi.popularmovies.popularstream.PopularStreamApiDatasource;
import com.movies.tobi.popularmovies.popularstream.PopularStreamRepository;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Subscriber;

public class PopularMoviesActivity extends AppCompatActivity {

    private static final int POSTER_COL_COUNT = 3;

    @Bind(R.id.popularMovies_recycler)
    RecyclerView popularMoviesRecycler;

    private PopularStreamRepository popularStreamRepository = new PopularStreamRepository(new PopularStreamApiDatasource());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popular_movies);
        ButterKnife.bind(this);

        popularMoviesRecycler.setLayoutManager(new GridLayoutManager(this, POSTER_COL_COUNT));
        popularMoviesRecycler.setHasFixedSize(true);

        popularStreamRepository.getPopularPosters().subscribe(new Subscriber<List<MoviePoster>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(List<MoviePoster> moviePosters) {
                popularMoviesRecycler.setAdapter(new MoviePosterAdapter(moviePosters));
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
