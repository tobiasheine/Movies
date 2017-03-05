package com.tobi.movies.runner;

import android.app.Application;
import android.content.Context;
import android.support.test.runner.AndroidJUnitRunner;

import com.tobi.movies.TestMovieApplication;

public class DaggerOverridesTestRunner extends AndroidJUnitRunner {

    @Override
    public Application newApplication(ClassLoader cl, String className, Context context) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        String testAppClassName = TestMovieApplication.class.getCanonicalName();
        return super.newApplication(cl, testAppClassName, context);
    }
}
