package com.movies.tobi.popularmovies.utils;

import android.os.Bundle;
import android.support.test.runner.AndroidJUnitRunner;

import com.movies.tobi.popularmovies.BuildConfig;

import cucumber.api.android.CucumberInstrumentationCore;

public class Instrumentation extends AndroidJUnitRunner {

    private static final String CUCUMBER_TAGS_KEY = "tags";

    private final CucumberInstrumentationCore instrumentationCore = new CucumberInstrumentationCore(this);

    @Override
    public void onCreate(final Bundle bundle) {
        final String tags = BuildConfig.TEST_TAGS;
        if (!tags.isEmpty()) {
            // Reformat tags list to separate items with '--' as expected by Cucumber library, see method
            // cucumber-android-1.2.2.jar\cucumber\runtime\android\Arguments.class @ appendOption()
            bundle.putString(CUCUMBER_TAGS_KEY, tags.replaceAll(",", "--").replaceAll("\\s", ""));
        }

        instrumentationCore.create(bundle);
        super.onCreate(bundle);
    }

    @Override
    public void onStart() {
        waitForIdleSync();
        instrumentationCore.start();
    }
}
