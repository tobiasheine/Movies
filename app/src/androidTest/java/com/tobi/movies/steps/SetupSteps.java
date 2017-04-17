package com.tobi.movies.steps;

import android.support.test.InstrumentationRegistry;

import com.tobi.movies.MovieApplication;
import com.tobi.movies.backend.ConfigurableBackend;
import com.tobi.movies.popularstream.TestPopularMoviesComponent;
import com.tobi.movies.utils.ActivityFinisher;

import cucumber.api.java.After;

public class SetupSteps {

    @After
    public void tearDown() {
        clearBackend();
        ActivityFinisher.finishOpenActivities();
    }

    private void clearBackend() {
        MovieApplication movieApplication = (MovieApplication) InstrumentationRegistry.getTargetContext().getApplicationContext();
        ConfigurableBackend backend = (ConfigurableBackend) ((TestPopularMoviesComponent) movieApplication.getPopularMoviesComponent()).backend();
        backend.clear();
    }
}
