package com.movies.tobi.popularmovies;

import android.os.AsyncTask;

import rx.Scheduler;
import rx.schedulers.Schedulers;

public class EspressoDependencies extends ApplicationDependencies {

    @Override
    protected Scheduler createSubscribeScheduler() {
        // IdlingResource is not needed since espresso waits for AsyncTask.THREAD_POOL_EXECUTOR
        return Schedulers.from(AsyncTask.THREAD_POOL_EXECUTOR);
    }
}
