package com.movies.tobi.popularmovies.steps;

import android.support.test.espresso.contrib.RecyclerViewActions;

import com.movies.tobi.popularmovies.R;
import com.movies.tobi.popularmovies.matchers.PosterMatcher;

import java.util.Map;

import cucumber.api.DataTable;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.*;
import static org.hamcrest.core.AllOf.allOf;

public class PopularMoviesSteps {

    @When("^I select the poster at position (\\d+)$")
    public void I_select_a_poster_at(final int position) {
        onView(withId(R.id.popularMovies_recycler))
                .perform(RecyclerViewActions.actionOnItemAtPosition(position, click()));
    }

    @When("^I expect to see the following movie posters$")
    public void I_expect_to_see_movie_posters(final DataTable dataTable) {
        for (Map<String, String> row : dataTable.asMaps(String.class, String.class)) {
            int position = Integer.valueOf(row.get("position"));
            String posterPath = row.get("posterPath");

            onView(withId(R.id.popularMovies_recycler))
                    .check(matches(PosterMatcher.hasPosterAt(position, posterPath)));
        }

    }

    @Then("^I expect to see the following movie details$")
    public void I_expect_to_see_the_movie_details(final DataTable dataTable) {
        if (dataTable.asMaps(String.class, String.class).size() > 1) {
            throw new IllegalArgumentException("We can just display one movie per time");
        }

        final Map<String, String> row = dataTable.asMaps(String.class, String.class).get(0);

        String movieTitle = row.get("movieTitle");
        String movieDetails = row.get("movieDetails");

        onView(allOf(withId(R.id.movieTitle), withText(movieTitle))).check(matches(isDisplayed()));
        onView(allOf(withId(R.id.movieOverview), withText(movieDetails))).check(matches(isDisplayed()));
    }
}
