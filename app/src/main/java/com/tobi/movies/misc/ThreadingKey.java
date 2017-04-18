package com.tobi.movies.misc;

import dagger.MapKey;

@MapKey
public @interface ThreadingKey {
    Threading value();
}
