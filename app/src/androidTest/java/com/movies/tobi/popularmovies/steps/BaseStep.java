package com.movies.tobi.popularmovies.steps;

import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.util.Log;

import com.movies.tobi.popularmovies.EspressoDependencies;
import com.movies.tobi.popularmovies.MovieApplication;
import com.movies.tobi.popularmovies.popularstream.PopularMoviesActivity;
import com.movies.tobi.popularmovies.utils.ActivityFinisher;

import org.junit.Rule;

import cucumber.api.java.After;
import cucumber.api.java.Before;

public class BaseStep {

    @Rule
    public static ActivityTestRule<PopularMoviesActivity> activityTestRule = new ActivityTestRule<>(
            PopularMoviesActivity.class);

    @Before
    protected void setUp() throws Exception {
        Log.d("cucumber", "called setup");
        activityTestRule.launchActivity(null);
        MovieApplication movieApplication = (MovieApplication) InstrumentationRegistry.getTargetContext().getApplicationContext();
        movieApplication.setDependencies(new EspressoDependencies());
    }

    @After
    protected void tearDown() throws Exception {
        ActivityFinisher.finishOpenActivities();
        Log.d("cucumber", "called tearDown");
    }
}
