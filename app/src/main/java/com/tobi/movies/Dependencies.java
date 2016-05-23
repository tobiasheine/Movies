package com.tobi.movies;

import com.tobi.movies.popularstream.PopularStreamRepository;
import com.tobi.movies.posterdetails.MovieDetailsPresenter;

public interface Dependencies {

    ImageLoader imageLoader();

    PopularStreamRepository streamRepository();

    MovieDetailsPresenter movieDetailsPresenter();
}
