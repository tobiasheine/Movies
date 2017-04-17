package com.tobi.movies.misc;

import android.os.AsyncTask;

import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

@Module
public class TestThreadingModule {

    @Provides
    @IntoMap
    @ThreadingKey(Threading.SUBSCRIBER)
    Scheduler provideSubscriberScheduler() {
        return Schedulers.from(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    @Provides
    @IntoMap
    @ThreadingKey(Threading.OBSERVER)
    Scheduler provideObserverScheduler() {
        return AndroidSchedulers.mainThread();
    }

}
