package com.tobi.movies.posterdetails;

import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;

import com.tobi.movies.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.AllOf.allOf;

public class PosterDetailsRobot {

    public static PosterDetailsRobot create() {
        return new PosterDetailsRobot();
    }

    public static PosterDetailsRobot launchDetailsScreen(long movieId, ActivityTestRule<MovieDetailsActivity> testRule) {
        testRule.launchActivity(MovieDetailsActivity.createIntentFor(movieId, InstrumentationRegistry.getInstrumentation()
                .getTargetContext()
                .getApplicationContext()));
        return new PosterDetailsRobot();
    }

    private PosterDetailsRobot() {
    }

    public PosterDetailsRobot checkMovieTitleIsDisplayed(String movieTitle) {
        onView(allOf(withId(R.id.movieTitle), withText(movieTitle))).check(matches(isDisplayed()));
        return this;
    }

    public PosterDetailsRobot checkMovieDescriptionIsDisplayed(String movieDescription) {
        onView(allOf(withId(R.id.movieOverview), withText(movieDescription))).check(matches(isDisplayed()));
        return this;
    }
}
