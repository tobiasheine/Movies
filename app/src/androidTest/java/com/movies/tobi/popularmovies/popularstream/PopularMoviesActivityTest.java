package com.movies.tobi.popularmovies.popularstream;

import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.movies.tobi.popularmovies.EspressoDependencies;
import com.movies.tobi.popularmovies.MovieApplication;
import com.movies.tobi.popularmovies.R;
import com.movies.tobi.popularmovies.backend.FakeBackend;
import com.movies.tobi.popularmovies.posterdetails.ApiMovieDetails;

import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class PopularMoviesActivityTest {

    private FakeBackend backend = new FakeBackend();

    public ActivityTestRule<PopularMoviesActivity> rule = new ActivityTestRule<PopularMoviesActivity>(PopularMoviesActivity.class) {
        @Override
        protected void beforeActivityLaunched() {
            MovieApplication movieApplication = (MovieApplication) InstrumentationRegistry.getTargetContext().getApplicationContext();
            movieApplication.setDependencies(new EspressoDependencies(backend));
        }
    };

    @Test
    public void shouldNavigateToMovieDetails() throws Exception {
        long movieId = 293660L;
        String posterPath = "/deadpool.jpg";
        String movieTitle = "Deadpool";
        String movieDescription = "Awesome movie";
        givenAddedStreamToBackend(movieId, posterPath);
        givenBackendReturnsMovieDetails(movieId, movieTitle, movieDescription, posterPath);
        rule.launchActivity(null);

        onView(withId(R.id.popularMovies_recycler))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        onView(withText(movieTitle)).check(matches(isDisplayed()));
        onView(withText(movieDescription)).check(matches(isDisplayed()));
    }

    private void givenAddedStreamToBackend(long movieId, String posterPath) {
        ApiMoviePoster poster = new ApiMoviePoster();
        poster.movieId = movieId;
        poster.posterPath = posterPath;

        backend.addToPopularStream(poster);
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
