package com.movies.tobi.popularmovies.test.steps;

import android.util.Log;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class PopularMoviesSteps {

    @Given("^the following remote movie posters exist$")
    public static void the_following_remote_customer_exist(final DataTable dataTable) {
        //Foo
        Log.d("cucumber", "the_following_remote_customer_exist");
    }

    @When("^I select the poster at position (\\d+)$")
    public static void I_select_a_poster(final int position) {
        //Bar
        Log.d("cucumber", "I_select_a_poster");
    }

    @Then("^I expect to see the following movie details$")
    public static void I_expect_to_see_the_movie_details(final DataTable dataTable) {
        //meh
        Log.d("cucumber", "I_expect_to_see_the_movie_details");
    }
}
