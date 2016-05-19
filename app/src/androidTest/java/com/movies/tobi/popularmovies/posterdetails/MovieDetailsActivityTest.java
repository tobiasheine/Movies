package com.movies.tobi.popularmovies.posterdetails;

import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.movies.tobi.popularmovies.EspressoDependencies;
import com.movies.tobi.popularmovies.MovieApplication;
import com.movies.tobi.popularmovies.backend.FakeBackend;

import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class MovieDetailsActivityTest {

    private FakeBackend backend = new FakeBackend();

    public ActivityTestRule<MovieDetailsActivity> rule = new ActivityTestRule<MovieDetailsActivity>(MovieDetailsActivity.class) {
        @Override
        protected void beforeActivityLaunched() {
            MovieApplication movieApplication = (MovieApplication) InstrumentationRegistry.getTargetContext().getApplicationContext();
            movieApplication.setDependencies(new EspressoDependencies(backend));
        }
    };

    @Test
    public void shouldShowMovieTitle() throws Exception {
        long movieId = 293660L;
        String movieTitle = "Deadpool";
        String movieDescription = "Based upon Marvel Comics’ most unconventional anti-hero, DEADPOOL tells the origin story of former Special Forces operative turned mercenary Wade Wilson, who after being subjected to a rogue experiment that leaves him with accelerated healing powers, adopts the alter ego Deadpool. Armed with his new abilities and a dark, twisted sense of humor, Deadpool hunts down the man who nearly destroyed his life.";
        String posterPath = "deadpool.jpg";
        givenBackendReturnsMovieDetails(movieId, movieTitle, movieDescription, posterPath);

        Intent intent = MovieDetailsActivity.createTestIntentFor(movieId);
        rule.launchActivity(intent);

        onView(withText(movieTitle)).check(matches(isDisplayed()));
        onView(withText(movieDescription)).check(matches(isDisplayed()));
    }

    private void givenBackendReturnsMovieDetails(long movieId, String movieTitle, String movieOverview, String posterPath) {
        ApiMovieDetails apiMovieDetails = new ApiMovieDetails();
        apiMovieDetails.originalTitle = movieTitle;
        apiMovieDetails.movieId = movieId;
        apiMovieDetails.overview = movieOverview;
        apiMovieDetails.posterPath = posterPath;
        backend.addMovieDetails(movieId, apiMovieDetails);
    }
}
