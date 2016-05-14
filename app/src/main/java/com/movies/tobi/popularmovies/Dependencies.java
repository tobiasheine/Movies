package com.movies.tobi.popularmovies;

import com.movies.tobi.popularmovies.popularstream.PopularStreamRepository;
import com.movies.tobi.popularmovies.posterdetails.MovieDetailsPresenter;

public interface Dependencies {

    ImageLoader imageLoader();

    PopularStreamRepository streamRepository();

    MovieDetailsPresenter movieDetailsPresenter();
}
