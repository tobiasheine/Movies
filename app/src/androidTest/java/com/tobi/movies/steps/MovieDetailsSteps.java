package com.tobi.movies.steps;

import com.tobi.movies.posterdetails.PosterDetailsRobot;

import java.util.Map;

import cucumber.api.DataTable;
import cucumber.api.java.en.Then;

public class MovieDetailsSteps {

    private final PosterDetailsRobot posterDetailsRobot = PosterDetailsRobot.create();

    @Then("^I expect to see the following movie details$")
    public void I_expect_to_see_the_movie_details(final DataTable dataTable) {
        if (dataTable.asMaps(String.class, String.class).size() > 1) {
            throw new IllegalArgumentException("We can just display one movie per time");
        }

        final Map<String, String> row = dataTable.asMaps(String.class, String.class).get(0);

        String movieTitle = row.get("movieTitle");
        String movieDetails = row.get("movieDetails");

        posterDetailsRobot.checkMovieTitleIsDisplayed(movieTitle);
        posterDetailsRobot.checkMovieDescriptionIsDisplayed(movieDetails);
    }
}
