package com.movies.tobi.popularmovies.steps;

import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;

import com.movies.tobi.popularmovies.EspressoDependencies;
import com.movies.tobi.popularmovies.MovieApplication;
import com.movies.tobi.popularmovies.backend.FakeBackend;
import com.movies.tobi.popularmovies.popularstream.ApiMoviePoster;
import com.movies.tobi.popularmovies.popularstream.PopularMoviesActivity;
import com.movies.tobi.popularmovies.posterdetails.ApiMovieDetails;
import com.movies.tobi.popularmovies.utils.ActivityFinisher;

import java.util.Map;

import cucumber.api.DataTable;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;

public class SetupSteps {

    private final FakeBackend backend = new FakeBackend();

    private final ActivityTestRule<PopularMoviesActivity> mActivityRule = new ActivityTestRule<>(
            PopularMoviesActivity.class);

    @Before
    public void setup() {
        MovieApplication movieApplication = (MovieApplication) InstrumentationRegistry.getTargetContext().getApplicationContext();
        movieApplication.setDependencies(new EspressoDependencies(backend));
    }

    @After
    public void tearDown() {
        ActivityFinisher.finishOpenActivities(); // Required for testing App with multiple activities
    }

    @Given("^I start the application$")
    public void I_start_app() {
        //FIXME: otherwise when running multiple tests after each other sth. the instrumentation times out when starting the activity
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        mActivityRule.launchActivity(null);
    }

    @Given("^the following remote movie posters exist$")
    public void the_following_remote_movie_poster_exist(final DataTable dataTable) {
        extractPostersFromDataTable(dataTable);
    }

    @Given("^the following remote movie details exist$")
    public void the_following_remote_movie_details_exist(final DataTable dataTable) {
        extractMovieDetailsFromDataTable(dataTable);
    }

    private void extractMovieDetailsFromDataTable(DataTable dataTable) {
        for (final Map<String, String> row : dataTable.asMaps(String.class, String.class)) {

            Long movieId = Long.valueOf(row.get("movieId"));
            String posterPath = row.get("posterPath");
            String title = row.get("title");
            String description = row.get("description");

            givenBackendReturnsMovieDetails(movieId, title, description, posterPath);
        }
    }

    private void extractPostersFromDataTable(DataTable dataTable) {
        for (final Map<String, String> row : dataTable.asMaps(String.class, String.class)) {
            Long movieId = Long.valueOf(row.get("movieId"));
            String posterPath = row.get("posterPath");

            givenBackendReturnsPopularStreamWith(movieId, posterPath);

        }
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
        backend.addMovieDetails(movieId, apiMovieDetails);
    }
}
