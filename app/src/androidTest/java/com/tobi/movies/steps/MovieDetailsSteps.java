package com.tobi.movies.steps;

import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;

import com.tobi.movies.Dependencies;
import com.tobi.movies.EspressoDependencies;
import com.tobi.movies.MovieApplication;
import com.tobi.movies.backend.ConfigurableBackend;
import com.tobi.movies.posterdetails.ApiMovieDetails;
import com.tobi.movies.posterdetails.MovieDetailsActivity;
import com.tobi.movies.posterdetails.PosterDetailsRobot;

import java.util.Map;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class MovieDetailsSteps {

    private final PosterDetailsRobot movieDetailsRobot = PosterDetailsRobot.create();

    @Given("^I start the details screen with movie id (\\d+)$")
    public void I_start_the_details_screen_with_movie(int movieId) {
        movieDetailsRobot.launchDetailsScreen(movieId, new ActivityTestRule<>(MovieDetailsActivity.class));
    }

    @Given("^the following remote movie details exist$")
    public void the_following_remote_movie_details_exist(final DataTable dataTable) {
        extractMovieDetailsFromDataTable(dataTable);
    }

    private void extractMovieDetailsFromDataTable(DataTable dataTable) {
        ConfigurableBackend configurableBackend = getConfigurableBackend();

        for (final Map<String, String> row : dataTable.asMaps(String.class, String.class)) {

            Long movieId = Long.valueOf(row.get("movieId"));
            String posterPath = row.get("posterPath");
            String title = row.get("title");
            String description = row.get("description");
            String releaseDate = row.get("releaseDate");

            configurableBackend.addMovieDetails(createMovieDetails(movieId, title, description, posterPath, releaseDate));
        }
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

    @Then("^I expect to see the following movie details$")
    public void I_expect_to_see_the_movie_details(final DataTable dataTable) {
        if (dataTable.asMaps(String.class, String.class).size() > 1) {
            throw new IllegalArgumentException("We can just display one movie per time");
        }

        final Map<String, String> row = dataTable.asMaps(String.class, String.class).get(0);

        String movieTitle = row.get("movieTitle");
        String movieDetails = row.get("movieDetails");

        movieDetailsRobot.checkMovieTitleIsDisplayed(movieTitle);
        movieDetailsRobot.checkMovieDescriptionIsDisplayed(movieDetails);
    }

    private ConfigurableBackend getConfigurableBackend() {
        MovieApplication movieApplication = (MovieApplication) InstrumentationRegistry.getTargetContext().getApplicationContext();
        Dependencies dependencies = movieApplication.getDependencies();
        return ((EspressoDependencies) dependencies).getConfigurableBackend();
    }
}
