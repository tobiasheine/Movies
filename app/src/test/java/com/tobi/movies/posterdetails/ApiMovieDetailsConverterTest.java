package com.tobi.movies.posterdetails;

import android.support.annotation.NonNull;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class ApiMovieDetailsConverterTest {

    private final ApiMovieDetailsConverter converter = new ApiMovieDetailsConverter();

    @Test
    public void shouldConvertVideo() throws Exception {
        String title = "title";
        String overview = "overview";
        String imageUrl = "imagePath.jpg";
        String releaseDate = "01.01.20190";
        long movieId = 1L;
        ApiMovieDetails apiMovieDetails = createApiMovieDetails(title, overview, imageUrl, movieId, releaseDate);

        MovieDetails convertedDetails = converter.convert(apiMovieDetails);

        assertMovieDetailsHas(convertedDetails, overview, imageUrl, movieId, title, releaseDate);
    }

    private void assertMovieDetailsHas(MovieDetails convertedDetails, String overview, String imageUrl, long movieId, String title, String releaseDate) {
        assertEquals(movieId, convertedDetails.movieId());
        assertEquals(title, convertedDetails.originalTitle());
        assertEquals(overview, convertedDetails.overview());
        assertEquals(releaseDate, convertedDetails.releaseDate());
        assertEquals("http://image.tmdb.org/t/p/w500/" + imageUrl, convertedDetails.posterPath());
    }

    @NonNull
    private ApiMovieDetails createApiMovieDetails(String title, String overview, String imageUrl, long movieId, String releaseDate) {
        ApiMovieDetails apiMovieDetails = new ApiMovieDetails();
        apiMovieDetails.movieId = movieId;
        apiMovieDetails.originalTitle = title;
        apiMovieDetails.overview = overview;
        apiMovieDetails.posterPath = "/" + imageUrl;
        apiMovieDetails.releaseDate = releaseDate;
        return apiMovieDetails;
    }
}
