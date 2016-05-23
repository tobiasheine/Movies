package com.movies.tobi.popularmovies.matchers;

import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.movies.tobi.popularmovies.popularstream.MoviePoster;
import com.movies.tobi.popularmovies.popularstream.MoviePosterAdapter;

import org.hamcrest.Description;
import org.hamcrest.Matcher;

public class PosterMatcher {

    public static Matcher<? super View> hasPosterAt(final int position, final String posterPath) {
        return new BoundedMatcher<View, RecyclerView>(RecyclerView.class) {
            @Override
            public void describeTo(Description description) {
                description.appendText("has poster with path:" + posterPath + " at position:" + position);
            }

            @Override
            protected boolean matchesSafely(RecyclerView item) {
                MoviePosterAdapter adapter = (MoviePosterAdapter) item.getAdapter();
                MoviePoster moviePoster = adapter.getItemAt(position);
                return moviePoster.getPosterPath().contains(posterPath);
            }
        };
    }
}
