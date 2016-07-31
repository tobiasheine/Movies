package com.tobi.movies.steps;

import com.tobi.movies.popularstream.PopularMoviesRobot;

import java.util.Map;

import cucumber.api.DataTable;
import cucumber.api.java.en.When;

public class PopularMoviesSteps {

    private final PopularMoviesRobot popularMoviesRobot = PopularMoviesRobot.create();

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
}
