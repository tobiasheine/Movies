package com.movies.tobi.popularmovies.posterdetails;

import org.junit.Ignore;
import org.junit.Test;

import rx.Observable;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MovieDetailsPresenterTest {

    private static final long VALID_MOVIE_ID = 1L;
    private static final long INVALID_MOVIE_ID = 2L;

    private final MovieDetailsRepository repository = mock(MovieDetailsRepository.class);
    private final MovieDetailsMVP.View view = mock(MovieDetailsMVP.View.class);

    private final MovieDetailsPresenter presenter = new MovieDetailsPresenter(repository);

    @Test
    public void shouldDisplayModel() throws Exception {
        MovieDetails model = mock(MovieDetails.class);
        when(repository.getMovieDetails(VALID_MOVIE_ID)).thenReturn(Observable.just(model));

        presenter.startPresenting(view, VALID_MOVIE_ID);

        verify(view).display(model);
    }

    @Ignore
    public void shouldDisplayError() throws Exception {
        when(repository.getMovieDetails(INVALID_MOVIE_ID)).thenReturn(Observable.<MovieDetails>error(new Exception()));

        presenter.startPresenting(view, VALID_MOVIE_ID);

        verify(view).showError();
    }
}
