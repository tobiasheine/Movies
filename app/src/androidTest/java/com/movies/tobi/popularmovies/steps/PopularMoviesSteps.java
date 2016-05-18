package com.movies.tobi.popularmovies.steps;

import android.support.test.espresso.contrib.RecyclerViewActions;

import com.movies.tobi.popularmovies.R;

import cucumber.api.DataTable;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.*;

public class PopularMoviesSteps {

    @When("^I select the poster at position (\\d+)$")
    public static void I_select_a_poster(final int position) {
        onView(withId(R.id.popularMovies_recycler))
                .perform(RecyclerViewActions.actionOnItemAtPosition(position, click()));

    }

    @Then("^I expect to see the following movie details$")
    public static void I_expect_to_see_the_movie_details(final DataTable dataTable) {
        onView(withText("Deadpool")).check(matches(isDisplayed()));
    }
}
