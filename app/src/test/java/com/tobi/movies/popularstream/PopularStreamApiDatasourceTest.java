package com.tobi.movies.popularstream;

import com.tobi.movies.backend.Backend;

import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import rx.Observable;
import rx.observers.TestSubscriber;

import static java.util.Arrays.asList;
import static org.mockito.Mockito.when;

public class PopularStreamApiDatasourceTest {

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Mock
    private Backend backend;

    private PopularStreamApiDatasource apiDatasource;

    @Before
    public void setUp() throws Exception {
        apiDatasource = new PopularStreamApiDatasource(backend);
    }

    @Test
    public void shouldReturnApiMoviePosters() throws Exception {
        ApiMoviePoster firstPoster = new ApiMoviePoster();
        firstPoster.movieId = 1L;
        firstPoster.posterPath = "url1";

        ApiMoviePoster secondPoster = new ApiMoviePoster();
        secondPoster.movieId = 2L;
        secondPoster.posterPath = "url2";

        ApiMoviePoster thirdPoster = new ApiMoviePoster();
        thirdPoster.movieId = 2L;
        thirdPoster.posterPath = "url2";

        List<ApiMoviePoster> expectedPosters = asList(firstPoster, secondPoster, thirdPoster);
        givenApiResponseWithMoviePoster(expectedPosters);

        TestSubscriber<List<ApiMoviePoster>> testSubscriber = TestSubscriber.create();
        Observable<List<ApiMoviePoster>> popularPosters = apiDatasource.getPopularPosters();
        popularPosters.subscribe(testSubscriber);

        testSubscriber.assertValue(expectedPosters);
    }

    private void givenApiResponseWithMoviePoster(List<ApiMoviePoster> posters) {
        ApiPopularMoviesResponse apiResponse = new ApiPopularMoviesResponse();
        apiResponse.results = posters;

        when(backend.popularStream()).thenReturn(Observable.just(apiResponse));
    }
}

