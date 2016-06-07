package com.tobi.movies.posterdetails;

public interface MovieDetailsMVP {

    interface Model {

        String overview();

        long movieId();

        String originalTitle();

        String posterPath();

        String releaseDate();
    }

    interface Presenter {
        void startPresenting(View movieDetailsView, long movieId);

        void stopPresenting();
    }

    interface View {
        void display(Model model);

        void showError();
    }
}
