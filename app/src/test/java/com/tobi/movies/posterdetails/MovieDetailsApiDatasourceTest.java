package com.tobi.movies.posterdetails;

import com.tobi.movies.BuildConfig;
import com.tobi.movies.backend.Backend;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import rx.Observable;
import rx.observers.TestSubscriber;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MovieDetailsApiDatasourceTest {

    private static final long KNOWN_MOVIE_ID = 1L;

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Mock
    private Backend backend;

    private MovieDetailsApiDatasource apiDatasource;

    @Before
    public void setUp() throws Exception {
        apiDatasource = new MovieDetailsApiDatasource(backend);
    }

    @Test
    public void shouldReturnMovieDetails() throws Exception {
        ApiMovieDetails movieDetails = mock(ApiMovieDetails.class);
        givenBackenReturnsMovieDetailsForId(KNOWN_MOVIE_ID, movieDetails);

        Observable<ApiMovieDetails> movieDetailsObservable = apiDatasource.getMovieDetails(KNOWN_MOVIE_ID);

        TestSubscriber<ApiMovieDetails> testSubscriber = new TestSubscriber<>();
        movieDetailsObservable.subscribe(testSubscriber);
        testSubscriber.assertValue(movieDetails);
    }

    private void givenBackenReturnsMovieDetailsForId(long movieId, ApiMovieDetails movieDetails) {
        when(backend.movieDetails(movieId, BuildConfig.API_KEY)).thenReturn(Observable.just(movieDetails));
    }
}
