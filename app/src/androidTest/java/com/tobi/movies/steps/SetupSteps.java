package com.tobi.movies.steps;

import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;

import com.tobi.movies.EspressoDependencies;
import com.tobi.movies.MovieApplication;
import com.tobi.movies.backend.ConfigurableBackend;
import com.tobi.movies.popularstream.ApiMoviePoster;
import com.tobi.movies.popularstream.PopularMoviesActivity;
import com.tobi.movies.posterdetails.ApiMovieDetails;
import com.tobi.movies.utils.ActivityFinisher;
import com.tobi.movies.popularstream.PopularMoviesRobot;

import java.util.Map;

import cucumber.api.DataTable;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;

public class SetupSteps {

    private final ActivityTestRule<PopularMoviesActivity> activityRule = new ActivityTestRule<>(
            PopularMoviesActivity.class);

    private PopularMoviesRobot movieRobot;
    private ConfigurableBackend configurableBackend;

    @Before
    public void setup() {
        MovieApplication movieApplication = (MovieApplication) InstrumentationRegistry.getTargetContext().getApplicationContext();
        configurableBackend = new ConfigurableBackend();
        movieApplication.setDependencies(new EspressoDependencies(configurableBackend));

        movieRobot = PopularMoviesRobot.create();
    }

    @After
    public void tearDown() {
        ActivityFinisher.finishOpenActivities(); // Required for testing App with multiple activities
    }

    @Given("^I start the application$")
    public void I_start_app() {
        //FIXME: https://github.com/tobiasheine/Movies/issues/5
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        movieRobot.launchPopularMovies(activityRule);
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
            String releaseDate = row.get("releaseDate");

            configurableBackend.addMovieDetails(createMovieDetails(movieId, title, description, posterPath, releaseDate));
        }
    }

    private void extractPostersFromDataTable(DataTable dataTable) {
        for (final Map<String, String> row : dataTable.asMaps(String.class, String.class)) {
            Long movieId = Long.valueOf(row.get("movieId"));
            String posterPath = row.get("posterPath");

            configurableBackend.addToPopularStream(createApiMoviePoster(movieId, posterPath));
        }
    }

    private ApiMoviePoster createApiMoviePoster(long movieId, String posterPath) {
        ApiMoviePoster poster = new ApiMoviePoster();
        poster.movieId = movieId;
        poster.posterPath = posterPath;
        return poster;
    }

    private ApiMovieDetails createMovieDetails(long movieId, String movieTitle, String movieOverview, String posterPath, String releaseDate) {
        ApiMovieDetails apiMovieDetails = new ApiMovieDetails();
        apiMovieDetails.originalTitle = movieTitle;
        apiMovieDetails.movieId = movieId;
        apiMovieDetails.overview = movieOverview;
        apiMovieDetails.posterPath = posterPath;
        apiMovieDetails.releaseDate = releaseDate;
        return apiMovieDetails;
    }
}
