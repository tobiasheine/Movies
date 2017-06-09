package com.tobi.movies.popularstream;

import android.support.annotation.NonNull;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class ApiMoviePosterConverterTest {

    private final ApiMoviePosterConverter apiMoviePosterConverter = new ApiMoviePosterConverter();

    @Test
    public void shouldConvertMoviePoster() throws Exception {
        String imageUrl = "imageUrl.jpg";
        long posterId = 1L;
        ApiMoviePoster apiMoviePoster = createApiMoviePoster(imageUrl, posterId);

        MoviePoster moviePoster = apiMoviePosterConverter.convert(apiMoviePoster);

        assertEquals("http://image.tmdb.org/t/p/w500/" + imageUrl, moviePoster.getPosterPath());
        assertEquals(posterId, moviePoster.getMovieId());
    }

    @NonNull
    private ApiMoviePoster createApiMoviePoster(String imageUrl, long posterId) {
        return new ApiMoviePoster(posterId, "/" + imageUrl);
    }
}
