package com.tobi.movies.utils;

import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;

import com.tobi.movies.R;
import com.tobi.movies.matchers.PosterMatcher;
import com.tobi.movies.popularstream.PopularMoviesActivity;
import com.tobi.movies.posterdetails.MovieDetailsActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.*;
import static org.hamcrest.core.AllOf.allOf;

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

    public MovieRobot launchDetailsScreen(long movieId, ActivityTestRule<MovieDetailsActivity> testRule) {
        testRule.launchActivity(MovieDetailsActivity.createIntentFor(movieId, InstrumentationRegistry.getInstrumentation()
                .getTargetContext()
                .getApplicationContext()));
        return this;
    }

    public MovieRobot launchPopularMovies(ActivityTestRule<PopularMoviesActivity> rule) {
        rule.launchActivity(null);
        return this;
    }

    public MovieRobot checkMovieTitleIsDisplayed(String movieTitle) {
        onView(allOf(withId(R.id.movieTitle), withText(movieTitle))).check(matches(isDisplayed()));
        return this;
    }

    public MovieRobot checkMovieDescriptionIsDisplayed(String movieDescription) {
        onView(allOf(withId(R.id.movieOverview), withText(movieDescription))).check(matches(isDisplayed()));
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
