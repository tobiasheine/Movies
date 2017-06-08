package com.tobi.movies.posterdetails;

import android.support.annotation.NonNull;

import org.joda.time.LocalDate;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class ApiMovieDetailsConverterTest {

    private final ApiMovieDetailsConverter converter = new ApiMovieDetailsConverter();

    @Test
    public void shouldConvertVideo() throws Exception {
        String title = "title";
        String overview = "overview";
        String imageUrl = "imagePath.jpg";
        String releaseDate = "2009-01-01";
        long movieId = 1L;
        ApiMovieDetails apiMovieDetails = createApiMovieDetails(title, overview, imageUrl, movieId, releaseDate);

        MovieDetails convertedDetails = converter.convert(apiMovieDetails);

        assertMovieDetailsHas(convertedDetails, overview, imageUrl, movieId, title, new LocalDate(releaseDate));
    }

    private void assertMovieDetailsHas(MovieDetails convertedDetails, String overview, String imageUrl, long movieId, String title, LocalDate releaseDate) {
        assertEquals(movieId, convertedDetails.getMovieId());
        assertEquals(title, convertedDetails.getOriginalTitle());
        assertEquals(overview, convertedDetails.getOverview());
        assertEquals(releaseDate, convertedDetails.getReleaseDate());
        assertEquals("http://image.tmdb.org/t/p/w500/" + imageUrl, convertedDetails.getPosterPath());
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
