package com.tobi.movies.utils;

import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;

import com.tobi.movies.backend.ConfigurableBackend;
import com.tobi.movies.popularstream.ApiMoviePoster;
import com.tobi.movies.posterdetails.ApiMovieDetails;
import com.tobi.movies.posterdetails.MovieDetailsActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

public class MovieRobot {

    private final ConfigurableBackend configurableBackend;
    private final ActivityTestRule<?> rule;

    public static MovieRobot createRobot(final ConfigurableBackend configurableBackend, ActivityTestRule<?> rule) {
        return new MovieRobot(configurableBackend, rule);
    }

    protected MovieRobot(ConfigurableBackend configurableBackend, ActivityTestRule<?> rule) {
        this.configurableBackend = configurableBackend;
        this.rule = rule;
    }

    public MovieRobot withRemoteMoviePosters(ApiMoviePoster... moviePosters) {
        configurableBackend.addToPopularStream(moviePosters);
        return this;
    }

    public MovieRobot addRemoteMovieDetails(ApiMovieDetails movieDetails) {
        configurableBackend.addMovieDetails(movieDetails);
        return this;
    }

    public MovieRobot launchDetailsScreen(long movieId) {
        rule.launchActivity(MovieDetailsActivity.createIntentFor(movieId, InstrumentationRegistry.getInstrumentation()
                .getTargetContext()
                .getApplicationContext()));
        return this;
    }

    public MovieRobot checkMovieTitleIsDisplayed(String movieTitle) {
        onView(withText(movieTitle)).check(matches(isDisplayed()));
        return this;
    }

    public MovieRobot checkMovieDescriptionIsDisplayed(String movieDescription) {
        onView(withText(movieDescription)).check(matches(isDisplayed()));
        return this;
    }
}
