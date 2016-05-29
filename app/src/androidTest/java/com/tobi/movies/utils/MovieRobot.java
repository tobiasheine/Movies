package com.tobi.movies.utils;

import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;

import com.tobi.movies.R;
import com.tobi.movies.backend.ConfigurableBackend;
import com.tobi.movies.matchers.PosterMatcher;
import com.tobi.movies.popularstream.ApiMoviePoster;
import com.tobi.movies.popularstream.PopularMoviesActivity;
import com.tobi.movies.posterdetails.ApiMovieDetails;
import com.tobi.movies.posterdetails.MovieDetailsActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

public class MovieRobot {

    private final ConfigurableBackend configurableBackend;

    public static MovieRobot createRobot(final ConfigurableBackend configurableBackend) {
        return new MovieRobot(configurableBackend);
    }

    protected MovieRobot(ConfigurableBackend configurableBackend) {
        this.configurableBackend = configurableBackend;
    }

    public MovieRobot withRemoteMoviePosters(ApiMoviePoster moviePosters) {
        configurableBackend.addToPopularStream(moviePosters);
        return this;
    }

    public MovieRobot addRemoteMovieDetails(ApiMovieDetails movieDetails) {
        configurableBackend.addMovieDetails(movieDetails);
        return this;
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
        onView(withText(movieTitle)).check(matches(isDisplayed()));
        return this;
    }

    public MovieRobot checkMovieDescriptionIsDisplayed(String movieDescription) {
        onView(withText(movieDescription)).check(matches(isDisplayed()));
        return this;
    }

    public MovieRobot checkPosterWithPathIsDisplayedAtPosition(int position, String posterPath) {
        onView(withId(R.id.popularMovies_recycler))
                .check(matches(PosterMatcher.hasPosterAt(position, posterPath)));

        return this;
    }
}
