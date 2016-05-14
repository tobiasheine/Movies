package com.movies.tobi.popularmovies.posterdetails;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import rx.Subscriber;

public class MovieDetailsPresenter implements MovieDetailsMVP.Presenter {

    private final MovieDetailsRepository repository;

    @Nullable
    private MovieDetailsMVP.View view;

    @Nullable
    private Subscriber<MovieDetails> subscriber;

    public MovieDetailsPresenter(MovieDetailsRepository repository) {
        this.repository = repository;
    }

    @Override
    public void startPresenting(MovieDetailsMVP.View movieDetailsView, long movieId) {
        this.view = movieDetailsView;
        subscriber = createSubscriber();
        repository.getMovieDetails(movieId).subscribe(subscriber);
    }

    @Override
    public void stopPresenting() {
        if (subscriber != null) {
            subscriber.unsubscribe();
        }
        view = null;
    }

    @NonNull
    private Subscriber<MovieDetails> createSubscriber() {
        return new Subscriber<MovieDetails>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                if (MovieDetailsPresenter.this.view != null) {
                    MovieDetailsPresenter.this.view.showError();
                }
            }

            @Override
            public void onNext(MovieDetails movieDetails) {
                if (MovieDetailsPresenter.this.view != null) {
                    MovieDetailsPresenter.this.view.display(movieDetails);
                }
            }
        };
    }
}
