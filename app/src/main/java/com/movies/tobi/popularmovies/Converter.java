package com.movies.tobi.popularmovies;

public interface Converter<T, R> {

    R convert(T input);
}
