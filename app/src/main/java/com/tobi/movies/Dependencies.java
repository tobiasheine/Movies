package com.tobi.movies;

import com.tobi.movies.popularstream.PopularStreamRepository;
import com.tobi.movies.posterdetails.MovieDetailsRepository;

public interface Dependencies {

    ImageLoader imageLoader();

    PopularStreamRepository streamRepository();

    MovieDetailsRepository movieDetailsRepository();
}
