package com.movies.tobi.popularmovies.posterdetails;

import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;

import rx.Subscriber;

public class MovieDetailsPresenter implements MovieDetailsMVP.Presenter {

    private final MovieDetailsRepository repository;
    private final MovieDetailsMVP.View view;
    private final long movieId;

    private final Subscriber<MovieDetails> subscriber;

    @VisibleForTesting
    MovieDetailsPresenter(MovieDetailsRepository repository, MovieDetailsMVP.View view, long movieId) {
        this.repository = repository;
        this.view = view;
        this.movieId = movieId;

        subscriber = createSubscriber();
    }

    public MovieDetailsPresenter(MovieDetailsMVP.View view, long movieId) {
        this(new MovieDetailsRepository(), view, movieId);
    }

    @Override
    public void startPresenting() {
        repository.getMovieDetails(movieId).subscribe(subscriber);
    }

    @Override
    public void stopPresenting() {
        subscriber.unsubscribe();
    }

    @NonNull
    private Subscriber<MovieDetails> createSubscriber() {
        return new Subscriber<MovieDetails>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                MovieDetailsPresenter.this.view.showError();
            }

            @Override
            public void onNext(MovieDetails movieDetails) {
                MovieDetailsPresenter.this.view.display(movieDetails);
            }
        };
    }
}
