package com.tobi.movies.misc;

import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

@Module
public class ThreadingModule {

    @IntoMap
    @ThreadingKey(Threading.SUBSCRIBER)
    @Provides
    Scheduler provideSubscriberScheduler() {
        return Schedulers.io();
    }

    @IntoMap
    @ThreadingKey(Threading.OBSERVER)
    @Provides
    Scheduler provideObserverScheduler() {
        return AndroidSchedulers.mainThread();
    }

}
