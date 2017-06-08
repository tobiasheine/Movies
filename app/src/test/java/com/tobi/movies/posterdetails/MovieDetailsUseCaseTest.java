package com.tobi.movies.posterdetails;

import org.joda.time.LocalDate;
import org.junit.Test;

import rx.Observable;
import rx.schedulers.Schedulers;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class MovieDetailsUseCaseTest {

    private static final long ANY_MOVIE_ID = 1;
    private static final MovieDetails ANY_MOVIE_DETAILS = new MovieDetails("overview", 1L, "title,", "path", new LocalDate());

    private final MovieDetailsRepository repository = mock(MovieDetailsRepository.class);
    private final MovieDetailsUseCase.Listener listener = mock(MovieDetailsUseCase.Listener.class);
    private final MovieDetailsUseCase movieDetailsUseCase = new MovieDetailsUseCase(repository, Schedulers.immediate(), Schedulers.immediate());

    @Test
    public void shouldTriggerListenerOnMovieDetails() throws Exception {
        when(repository.getMovieDetails(ANY_MOVIE_ID)).thenReturn(Observable.just(ANY_MOVIE_DETAILS));
        movieDetailsUseCase.setListener(listener);

        movieDetailsUseCase.loadMovieDetails(ANY_MOVIE_ID);

        verify(listener).onMovieDetails(ANY_MOVIE_DETAILS);
    }

    @Test
    public void shouldNoOpWhenNoListenerAttached() throws Exception {
        when(repository.getMovieDetails(ANY_MOVIE_ID)).thenReturn(Observable.just(ANY_MOVIE_DETAILS));

        movieDetailsUseCase.loadMovieDetails(ANY_MOVIE_ID);

        verifyZeroInteractions(listener);
    }

    @Test
    public void shouldReturnLastMovieDetails() throws Exception {
        when(repository.getMovieDetails(ANY_MOVIE_ID)).thenReturn(Observable.just(ANY_MOVIE_DETAILS));
        movieDetailsUseCase.loadMovieDetails(ANY_MOVIE_ID);

        MovieDetails movieDetails = movieDetailsUseCase.getMovieDetails();

        assertEquals(ANY_MOVIE_DETAILS, movieDetails);
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
