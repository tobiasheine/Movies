package com.tobi.movies.posterdetails;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InOrder;

import static org.mockito.Mockito.*;

public class MovieDetailsPresenterTest {

    private static final long ANY_MOVIE_ID = 1L;
    private static final MovieDetails ANY_MOVIE_DETAILS = MovieDetails.builder()
            .movieId(ANY_MOVIE_ID)
            .originalTitle("title")
            .overview("overview")
            .posterPath("poster")
            .releaseDate("date")
            .build();

    private final MovieDetailsMVP.View view = mock(MovieDetailsMVP.View.class);
    private final MovieDetailsUseCase movieDetailsUseCase = mock(MovieDetailsUseCase.class);

    private final MovieDetailsPresenter presenter = new MovieDetailsPresenter(movieDetailsUseCase);

    @Test
    public void shouldDisplayMovieDetailsIfAvailable() throws Exception {
        when(movieDetailsUseCase.getMovieDetails()).thenReturn(ANY_MOVIE_DETAILS);

        presenter.startPresenting(view, ANY_MOVIE_ID);

        verify(view).display(ANY_MOVIE_DETAILS);
        verify(movieDetailsUseCase, never()).loadMovieDetails(anyLong());
        verify(movieDetailsUseCase, never()).setListener(any(MovieDetailsUseCase.Listener.class));
    }

    @Test
    public void shouldSetListenerAndLoadMovieDetailsIfNotAvailable() throws Exception {
        when(movieDetailsUseCase.getMovieDetails()).thenReturn(null);

        presenter.startPresenting(view, ANY_MOVIE_ID);

        InOrder order = inOrder(movieDetailsUseCase);
        order.verify(movieDetailsUseCase).setListener(any(MovieDetailsUseCase.Listener.class));
        order.verify(movieDetailsUseCase).loadMovieDetails(ANY_MOVIE_ID);
    }

    @Test
    public void shouldBindListenerToView() throws Exception {
        Exception exception = new Exception();
        when(movieDetailsUseCase.getMovieDetails()).thenReturn(null);
        ArgumentCaptor<MovieDetailsUseCase.Listener> listenerCapture = ArgumentCaptor.forClass(MovieDetailsUseCase.Listener.class);
        presenter.startPresenting(view, ANY_MOVIE_ID);

        verify(movieDetailsUseCase).setListener(listenerCapture.capture());

        MovieDetailsUseCase.Listener listener = listenerCapture.getValue();
        listener.onMovieDetails(ANY_MOVIE_DETAILS);
        verify(view).display(ANY_MOVIE_DETAILS);
        listener.onError(exception);
        verify(view).showError();
    }
}
