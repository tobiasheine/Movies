package com.tobi.movies.steps;

import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;

import com.tobi.movies.MovieApplication;
import com.tobi.movies.backend.ConfigurableBackend;
import com.tobi.movies.popularstream.ApiMoviePoster;
import com.tobi.movies.popularstream.PopularMoviesActivity;
import com.tobi.movies.popularstream.PopularMoviesRobot;
import com.tobi.movies.popularstream.TestPopularMoviesComponent;

import java.util.Map;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;

public class PopularMoviesSteps {

    private final PopularMoviesRobot popularMoviesRobot = PopularMoviesRobot.create();

    @Given("^I start the application$")
    public void I_start_app() {
        popularMoviesRobot.launchPopularMovies(new ActivityTestRule<>(PopularMoviesActivity.class));
    }

    @Given("^the following remote movie posters exist$")
    public void the_following_remote_movie_poster_exist(final DataTable dataTable) {
        extractPostersFromDataTable(dataTable);
    }

    private void extractPostersFromDataTable(DataTable dataTable) {
        ConfigurableBackend configurableBackend = getConfigurableBackend();

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

    @When("^I select the poster at position (\\d+)$")
    public void I_select_a_poster_at(final int position) {
        popularMoviesRobot.selectPosterAtPosition(position);
    }

    @When("^I expect to see the following movie posters$")
    public void I_expect_to_see_movie_posters(final DataTable dataTable) {
        for (Map<String, String> row : dataTable.asMaps(String.class, String.class)) {
            int position = Integer.valueOf(row.get("position"));
            String posterPath = row.get("posterPath");

            popularMoviesRobot.checkPosterWithPathIsDisplayedAtPosition(position, posterPath);
        }
    }

    private ConfigurableBackend getConfigurableBackend() {
        MovieApplication movieApplication = (MovieApplication) InstrumentationRegistry.getTargetContext().getApplicationContext();
        return (ConfigurableBackend) ((TestPopularMoviesComponent) movieApplication.getPopularMoviesComponent()).backend();
    }
}
