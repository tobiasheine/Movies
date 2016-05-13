package com.movies.tobi.popularmovies.posterdetails;

public interface MovieDetailsMVP {

    interface Model {

        String getOverview();

        long getMovieId();

        String getOriginalTitle();

        String getPosterPath();
    }

    interface Presenter {
        void startPresenting();

        void stopPresenting();
    }

    interface View {
        void display(Model model);

        void showError();
    }
}
