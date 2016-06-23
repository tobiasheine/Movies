package com.tobi.movies.posterdetails;

import org.junit.Test;

import rx.Observable;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class MovieDetailsUseCaseTest {

    private static final long ANY_MOVIE_ID = 1;
    private final MovieDetailsRepository repository = mock(MovieDetailsRepository.class);
    private final MovieDetailsUseCase.Listener listener = mock(MovieDetailsUseCase.Listener.class);

    private final MovieDetailsUseCase movieDetailsUseCase = new MovieDetailsUseCase(repository);

    @Test
    public void shouldTriggerListenerOnMovieDetails() throws Exception {
        MovieDetails movieDetails = mock(MovieDetails.class);
        when(repository.getMovieDetails(ANY_MOVIE_ID)).thenReturn(Observable.just(movieDetails));
        movieDetailsUseCase.setListener(listener);

        movieDetailsUseCase.loadMovieDetails(ANY_MOVIE_ID);

        verify(listener).onMovieDetails(movieDetails);
    }

    @Test
    public void shouldNoOpWhenNoListenerAttached() throws Exception {
        MovieDetails movieDetails = mock(MovieDetails.class);
        when(repository.getMovieDetails(ANY_MOVIE_ID)).thenReturn(Observable.just(movieDetails));

        movieDetailsUseCase.loadMovieDetails(ANY_MOVIE_ID);

        verifyZeroInteractions(listener);
    }

    @Test
    public void shouldReturnLastMovieDetails() throws Exception {
        MovieDetails expectedMovieDetails = mock(MovieDetails.class);
        when(repository.getMovieDetails(ANY_MOVIE_ID)).thenReturn(Observable.just(expectedMovieDetails));
        movieDetailsUseCase.loadMovieDetails(ANY_MOVIE_ID);

        MovieDetails movieDetails = movieDetailsUseCase.getMovieDetails();

        assertEquals(expectedMovieDetails, movieDetails);
    }

    @Test
    public void shouldTriggerListenerOnError() throws Exception {
        Exception exception = new Exception();
        when(repository.getMovieDetails(ANY_MOVIE_ID)).thenReturn(Observable.<MovieDetails>error(exception));
        movieDetailsUseCase.setListener(listener);

        movieDetailsUseCase.loadMovieDetails(ANY_MOVIE_ID);

        verify(listener).onError(exception);
    }
}
