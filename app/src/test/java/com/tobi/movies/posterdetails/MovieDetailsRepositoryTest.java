package com.tobi.movies.posterdetails;

import android.support.annotation.NonNull;

import com.tobi.movies.Converter;

import org.joda.time.LocalDate;
import org.junit.Test;

import rx.Observable;
import rx.observers.TestSubscriber;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MovieDetailsRepositoryTest {

    private final MovieDetailsApiDatasource apiDatasource = mock(MovieDetailsApiDatasource.class);
    private final Converter<ApiMovieDetails, MovieDetails> converter = new ApiMovieDetailsConverter();

    private final MovieDetailsRepository repository = new MovieDetailsRepository(apiDatasource, converter);

    @Test
    public void shouldReturnMovieDetailsForId() throws Exception {
        long movieId = 1L;
        String imagePath = "/path";
        String overview = "overview";
        String title = "title";
        String releaseDate = "2001-01-01";
        ApiMovieDetails apiMovieDetails = createApiMovieDetails(movieId, imagePath, overview, title, releaseDate);
        when(apiDatasource.getMovieDetails(movieId)).thenReturn(Observable.just(apiMovieDetails));

        Observable<MovieDetails> detailsObservable = repository.getMovieDetails(movieId);

        TestSubscriber<MovieDetails> testSubscriber = new TestSubscriber<>();
        detailsObservable.subscribe(testSubscriber);
        MovieDetails expectedMovieDetails = MovieDetails.builder().
                movieId(movieId).
                posterPath("http://image.tmdb.org/t/p/w500/" + imagePath.substring(1, imagePath.length())).
                originalTitle(title).
                releaseDate(new LocalDate(releaseDate)).
                overview(overview).
                build();
        testSubscriber.assertValue(expectedMovieDetails);
    }

    @NonNull
    private ApiMovieDetails createApiMovieDetails(long movieId, String imagePath, String overview, String title, String releaseDate) {
        ApiMovieDetails apiMovieDetails = new ApiMovieDetails();
        apiMovieDetails.movieId = movieId;
        apiMovieDetails.posterPath = imagePath;
        apiMovieDetails.overview = overview;
        apiMovieDetails.originalTitle = title;
        apiMovieDetails.releaseDate = releaseDate;
        return apiMovieDetails;
    }
}
