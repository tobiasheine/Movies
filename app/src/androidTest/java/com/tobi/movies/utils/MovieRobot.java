package com.tobi.movies.utils;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;

import com.tobi.movies.R;
import com.tobi.movies.matchers.PosterMatcher;
import com.tobi.movies.popularstream.PopularMoviesActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

public class MovieRobot {

    private static MovieRobot INSTANCE;

    public static MovieRobot create() {
        INSTANCE = new MovieRobot();
        return INSTANCE;
    }

    public static MovieRobot get() {
        if (INSTANCE != null) {
            return INSTANCE;
        }

        throw new IllegalStateException("MovieRobot not created");
    }

    public static void reset() {
        INSTANCE = null;
    }

    private MovieRobot() {
    }

    public MovieRobot launchPopularMovies(ActivityTestRule<PopularMoviesActivity> rule) {
        rule.launchActivity(null);
        return this;
    }

    public MovieRobot checkPosterWithPathIsDisplayedAtPosition(int position, String posterPath) {
        onView(withId(R.id.popularMovies_recycler))
                .check(matches(PosterMatcher.hasPosterAt(position, posterPath)));
        return this;
    }

    public MovieRobot selectPosterAtPosition(int position) {
        onView(withId(R.id.popularMovies_recycler))
                .perform(RecyclerViewActions.actionOnItemAtPosition(position, click()));
        return this;
    }
}
