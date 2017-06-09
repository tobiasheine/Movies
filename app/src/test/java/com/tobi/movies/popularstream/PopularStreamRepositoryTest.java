package com.tobi.movies.popularstream;

import android.support.annotation.NonNull;

import com.google.common.collect.Lists;
import com.tobi.movies.Converter;
import com.tobi.movies.misc.Threading;

import java.util.HashMap;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import rx.Observable;
import rx.Scheduler;
import rx.observers.TestSubscriber;
import rx.schedulers.Schedulers;

import static org.mockito.Mockito.when;

public class PopularStreamRepositoryTest {

    private static final long FIRST_MOVIE_ID = 1L;
    private static final long SECOND_MOVIE_ID = 2L;
    private static final String FIRST_POSTER_PATH = "first path";
    private static final String SECOND_POSTER_PATH = "second path";

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Mock
    private PopularStreamApiDatasource apiDataSource;

    @Mock
    private Converter<ApiMoviePoster, MoviePoster> posterConverter;

    private PopularStreamRepository streamRepository;

    @Before
    public void setUp() throws Exception {
        HashMap<Threading, Scheduler> schedulerHashMap = new HashMap<>();
        schedulerHashMap.put(Threading.SUBSCRIBER, Schedulers.immediate());
        schedulerHashMap.put(Threading.OBSERVER, Schedulers.immediate());

        streamRepository = new PopularStreamRepository(
                apiDataSource,
                schedulerHashMap,
                posterConverter
        );
    }

    @Test
    public void shouldReturnMoviePosters() throws Exception {
        // given
        ApiMoviePoster firstPoster = createApiMoviePoster(FIRST_MOVIE_ID, FIRST_POSTER_PATH);
        ApiMoviePoster secondPoster = createApiMoviePoster(SECOND_MOVIE_ID, SECOND_POSTER_PATH);
        givenApiDataSourceReturns(firstPoster, secondPoster);
        givenConverterConvertsPosters(firstPoster, secondPoster);

        // when
        Observable<List<MoviePoster>> popularPosters = streamRepository.getPopularPosters();
        TestSubscriber<List<MoviePoster>> testSubscriber = new TestSubscriber<>();
        popularPosters.subscribe(testSubscriber);

        // then
        List<MoviePoster> moviePosters = Lists.newArrayList(
                new MoviePoster(FIRST_MOVIE_ID, FIRST_POSTER_PATH),
                new MoviePoster(SECOND_MOVIE_ID, SECOND_POSTER_PATH)
        );

        testSubscriber.assertValue(moviePosters);
    }

    private void givenConverterConvertsPosters(ApiMoviePoster... posters) {
        for (ApiMoviePoster poster : posters) {
            when(posterConverter.convert(poster)).thenReturn(new MoviePoster(poster.getMovieId(), poster.getPosterPath()));
        }
    }

    private void givenApiDataSourceReturns(ApiMoviePoster firstPoster, ApiMoviePoster secondPoster) {
        List<ApiMoviePoster> posterList = Lists.newArrayList(firstPoster, secondPoster);
        Observable<List<ApiMoviePoster>> listObservable = Observable.just(posterList);
        when(apiDataSource.getPopularPosters()).thenReturn(listObservable);
    }

    @NonNull
    private ApiMoviePoster createApiMoviePoster(long firstMovieId, String firstPosterPath) {
        return new ApiMoviePoster(firstMovieId, firstPosterPath);
    }
}
