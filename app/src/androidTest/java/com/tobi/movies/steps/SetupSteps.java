package com.tobi.movies.steps;

import com.tobi.movies.utils.ActivityFinisher;

import cucumber.api.java.After;

public class SetupSteps {

    @After
    public void tearDown() {
        ActivityFinisher.finishOpenActivities(); // Required for testing App with multiple activities
    }
}
