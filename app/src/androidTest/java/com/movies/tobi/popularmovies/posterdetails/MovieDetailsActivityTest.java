package com.movies.tobi.popularmovies.posterdetails;

import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.movies.tobi.popularmovies.EspressoDependencies;
import com.movies.tobi.popularmovies.MovieApplication;

import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class MovieDetailsActivityTest {

    public ActivityTestRule<MovieDetailsActivity> rule = new ActivityTestRule<MovieDetailsActivity>(MovieDetailsActivity.class) {
        @Override
        protected void beforeActivityLaunched() {
            MovieApplication movieApplication = (MovieApplication) InstrumentationRegistry.getTargetContext().getApplicationContext();
            movieApplication.setDependencies(new EspressoDependencies());
        }
    };

    @Test
    public void shouldShowMovieTitle() throws Exception {
        long movieId = 100L;
        String movieTitle = "Lock, Stock and Two Smoking Barrels";
        Intent intent = MovieDetailsActivity.createTestIntentFor(movieId);

        rule.launchActivity(intent);

        onView(withText(movieTitle)).check(matches(isDisplayed()));
    }
}
