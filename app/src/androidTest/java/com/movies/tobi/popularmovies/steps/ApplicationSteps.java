package com.movies.tobi.popularmovies.steps;

import android.util.Log;

import cucumber.api.java.en.Given;

public class ApplicationSteps {

    @Given("^I launch the application$")
    public void launchApplication() {
        Log.d("cucumber", "launch app");
    }

}
