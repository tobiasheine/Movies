package com.tobi.movies.popularstream;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;

import com.tobi.movies.R;
import com.tobi.movies.matchers.PosterMatcher;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

public class PopularMoviesRobot {


    public static PopularMoviesRobot create() {
        return new PopularMoviesRobot();
    }

    private PopularMoviesRobot() {
    }

    public PopularMoviesRobot launchPopularMovies(ActivityTestRule<PopularMoviesActivity> rule) {
        rule.launchActivity(null);
        return this;
    }

    public PopularMoviesRobot checkPosterWithPathIsDisplayedAtPosition(int position, String posterPath) {
        onView(withId(R.id.popularMovies_recycler))
                .check(matches(PosterMatcher.hasPosterAt(position, posterPath)));
        return this;
    }

    public PopularMoviesRobot selectPosterAtPosition(int position) {
        onView(withId(R.id.popularMovies_recycler))
                .perform(RecyclerViewActions.actionOnItemAtPosition(position, click()));
        return this;
    }
}
