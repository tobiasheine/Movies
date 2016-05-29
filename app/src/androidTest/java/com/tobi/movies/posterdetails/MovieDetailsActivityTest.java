package com.tobi.movies.posterdetails;

import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.tobi.movies.EspressoDependencies;
import com.tobi.movies.MovieApplication;
import com.tobi.movies.backend.ConfigurableBackend;

import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class MovieDetailsActivityTest {

    private ConfigurableBackend backend = new ConfigurableBackend();

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
        String movieDescription = "Such an awesome movie!";
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
        backend.addMovieDetails(apiMovieDetails);
    }
}
