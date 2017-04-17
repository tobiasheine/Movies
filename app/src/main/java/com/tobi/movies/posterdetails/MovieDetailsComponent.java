package com.tobi.movies.posterdetails;

import com.tobi.movies.backend.BackendModule;
import com.tobi.movies.misc.ImageModule;
import com.tobi.movies.misc.ThreadingModule;

import dagger.Component;

@Component(
        modules = {
                BackendModule.class,
                ImageModule.class,
                ThreadingModule.class
        }
)
public interface MovieDetailsComponent {

    void inject(MovieDetailsActivity movieDetailsActivity);
}
