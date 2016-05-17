package com.movies.tobi.popularmovies.test;

import android.os.Bundle;

import cucumber.api.android.CucumberInstrumentationCore;

public class Instrumentation extends android.support.test.runner.AndroidJUnitRunner {

    private final CucumberInstrumentationCore instrumentationCore = new CucumberInstrumentationCore(this);

    @Override
    public void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        instrumentationCore.create(bundle);
    }

    @Override
    public void onStart() {
        waitForIdleSync();
        instrumentationCore.start();
    }
}
