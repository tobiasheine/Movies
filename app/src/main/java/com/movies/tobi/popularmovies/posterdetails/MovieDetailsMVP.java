package com.movies.tobi.popularmovies.posterdetails;

public interface MovieDetailsMVP {

    interface Model {

    }

    interface Presenter {
        void startPresenting();

        void stopPresenting();

    }

    interface View {
        void display(Model model);
    }
}
