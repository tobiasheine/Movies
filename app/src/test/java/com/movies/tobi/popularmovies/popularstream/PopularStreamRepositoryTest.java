package com.movies.tobi.popularmovies.popularstream;

import android.support.annotation.NonNull;

import com.google.common.collect.Lists;
import com.movies.tobi.popularmovies.Converter;

import java.util.List;

import org.junit.Test;

import rx.Observable;
import rx.observers.TestSubscriber;
import rx.schedulers.Schedulers;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PopularStreamRepositoryTest {

    private PopularStreamApiDatasource apiDataSource = mock(PopularStreamApiDatasource.class);
    private Converter<ApiMoviePoster, MoviePoster> posterConverter = mock(Converter.class);

    private final PopularStreamRepository streamRepository = new PopularStreamRepository(apiDataSource, Schedulers.immediate(), Schedulers.immediate(),
                                                                                         posterConverter
    );
    public static final long FIRST_MOVIE_ID = 1L;
    public static final long SECOND_MOVIE_ID = 2L;
    public static final String FIRST_POSTER_PATH = "first path";
    public static final String SECOND_POSTER_PATH = "second path";

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
            when(posterConverter.convert(poster)).thenReturn(new MoviePoster(poster.movieId, poster.posterPath));
        }
    }

    private void givenApiDataSourceReturns(ApiMoviePoster firstPoster, ApiMoviePoster secondPoster) {
        List<ApiMoviePoster> posterList = Lists.newArrayList(firstPoster, secondPoster);
        Observable<List<ApiMoviePoster>> listObservable = Observable.just(posterList);
        when(apiDataSource.getPopularPosters()).thenReturn(listObservable);
    }

    @NonNull
    private ApiMoviePoster createApiMoviePoster(long firstMovieId, String firstPosterPath) {
        ApiMoviePoster firstPoster = new ApiMoviePoster();
        firstPoster.movieId = firstMovieId;
        firstPoster.posterPath = firstPosterPath;
        return firstPoster;
    }
}
