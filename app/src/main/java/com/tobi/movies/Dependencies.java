package com.tobi.movies;

import com.tobi.movies.backend.Backend;
import com.tobi.movies.popularstream.PopularStreamRepository;
import com.tobi.movies.posterdetails.MovieDetailsRepository;

import rx.Scheduler;

public interface Dependencies {

    PopularStreamRepository streamRepository();

    MovieDetailsRepository movieDetailsRepository();

    Scheduler createSubscriberThread();

    Scheduler createObserverThread();

    void setBackend(Backend backend);
}
