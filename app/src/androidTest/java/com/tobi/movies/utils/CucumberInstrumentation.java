package com.tobi.movies.utils;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.support.test.runner.AndroidJUnitRunner;

import com.tobi.movies.BuildConfig;
import com.tobi.movies.TestMovieApplication;

import cucumber.api.android.CucumberInstrumentationCore;

public class CucumberInstrumentation extends AndroidJUnitRunner {

    private static final String CUCUMBER_TAGS_KEY = "tags";
    public static final String CUCUMBER_SCENARIO_KEY = "name";
    private static final String CUCUMBER_FEATURE_KEY = "features";

    private final CucumberInstrumentationCore instrumentationCore = new CucumberInstrumentationCore(this);

    @Override
    public void onCreate(final Bundle bundle) {
        String tags = BuildConfig.TEST_TAGS;
        if (!tags.isEmpty()) {
            String tagsKey = tags.replaceAll("\\s", "");
            bundle.putString(CUCUMBER_TAGS_KEY, tagsKey);
        }

        String scenario = BuildConfig.TEST_SCENARIO;
        if (!scenario.isEmpty()) {
            scenario = scenario.replaceAll(" ", "\\\\s");
            bundle.putString(CUCUMBER_SCENARIO_KEY, scenario);
        }

        String feature = BuildConfig.TEST_FEATURE;
        if (!feature.isEmpty()) {
            bundle.putString(CUCUMBER_FEATURE_KEY, feature);
        }

        instrumentationCore.create(bundle);
        super.onCreate(bundle);
    }

    @Override
    public void onStart() {
        waitForIdleSync();
        instrumentationCore.start();
    }

    @Override
    public Application newApplication(ClassLoader cl, String className, Context context) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        String testAppClassName = TestMovieApplication.class.getCanonicalName();
        return super.newApplication(cl, testAppClassName, context);
    }
}
