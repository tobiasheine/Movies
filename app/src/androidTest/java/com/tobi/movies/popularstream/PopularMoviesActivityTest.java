package com.tobi.movies.popularstream;

import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.tobi.movies.EspressoDependencies;
import com.tobi.movies.MovieApplication;
import com.tobi.movies.R;
import com.tobi.movies.backend.ConfigurableBackend;
import com.tobi.movies.matchers.PosterMatcher;
import com.tobi.movies.posterdetails.ApiMovieDetails;

import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.*;

@RunWith(AndroidJUnit4.class)
public class PopularMoviesActivityTest {

    private static final long MOVIE_ID = 293660L;
    private static final String POSTER_PATH = "deadpool.jpg";
    private static final String MOVIE_TITLE = "Deadpool";
    private static final String MOVIE_DESCRIPTION = "Awesome movie";

    private final ActivityTestRule<PopularMoviesActivity> rule = new ActivityTestRule<PopularMoviesActivity>(PopularMoviesActivity.class) {
        @Override
        protected void beforeActivityLaunched() {
            MovieApplication movieApplication = (MovieApplication) InstrumentationRegistry.getTargetContext().getApplicationContext();
            movieApplication.setDependencies(new EspressoDependencies(backend));
        }
    };

    private final ConfigurableBackend backend = new ConfigurableBackend();

    @Test
    public void shouldShowPoster() throws Exception {
        givenBackendReturnsPopularStreamWith(MOVIE_ID, POSTER_PATH);

        rule.launchActivity(null);

        onView(withId(R.id.popularMovies_recycler))
                .check(matches(PosterMatcher.hasPosterAt(0, POSTER_PATH)));
    }

    @Test
    public void shouldNavigateToMovieDetails() throws Exception {
        givenBackendReturnsPopularStreamWith(MOVIE_ID, POSTER_PATH);
        givenBackendReturnsMovieDetails(MOVIE_ID, MOVIE_TITLE, MOVIE_DESCRIPTION, POSTER_PATH);
        rule.launchActivity(null);

        onView(withId(R.id.popularMovies_recycler))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        onView(withText(MOVIE_TITLE)).check(matches(isDisplayed()));
        onView(withText(MOVIE_DESCRIPTION)).check(matches(isDisplayed()));
    }

    private void givenBackendReturnsPopularStreamWith(long movieId, String posterPath) {
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
        backend.addMovieDetails(apiMovieDetails);
    }
}
