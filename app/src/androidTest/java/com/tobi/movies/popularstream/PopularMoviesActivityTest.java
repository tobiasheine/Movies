package com.tobi.movies.popularstream;

import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.tobi.movies.MovieApplication;
import com.tobi.movies.backend.ConfigurableBackend;
import com.tobi.movies.posterdetails.ApiMovieDetails;
import com.tobi.movies.posterdetails.PosterDetailsRobot;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class PopularMoviesActivityTest {

    private static final long MOVIE_ID = 293660L;
    private static final String POSTER_PATH = "deadpool.jpg";
    private static final String MOVIE_TITLE = "Deadpool";
    private static final String MOVIE_DESCRIPTION = "Awesome movie";
    private static final String RELEASE_DATE = "2000-01-01";

    private ActivityTestRule<PopularMoviesActivity> rule;
    private ConfigurableBackend backend;
    private ApiMoviePoster apiMoviePoster;
    private ApiMovieDetails movieDetails;

    @Before
    public void setUp() throws Exception {
        MovieApplication movieApplication = (MovieApplication) InstrumentationRegistry.getTargetContext().getApplicationContext();
        backend = (ConfigurableBackend) ((TestPopularMoviesComponent) movieApplication.getPopularMoviesComponent()).backend();

        rule = new ActivityTestRule<>(PopularMoviesActivity.class);

        apiMoviePoster = createApiMoviePoster(MOVIE_ID, POSTER_PATH);
        movieDetails = createMovieDetails(MOVIE_ID, MOVIE_TITLE, MOVIE_DESCRIPTION, POSTER_PATH, RELEASE_DATE);
    }

    @After
    public void tearDown() throws Exception {
        backend.clear();
    }

    @Test
    public void shouldShowPoster() throws Exception {
        backend.addToPopularStream(apiMoviePoster);

        PopularMoviesRobot
                .create()
                .launchPopularMovies(rule)
                .checkPosterWithPathIsDisplayedAtPosition(0, POSTER_PATH);
    }

    @Test
    public void shouldNavigateToMovieDetails() throws Exception {
        backend.addToPopularStream(apiMoviePoster);
        backend.addMovieDetails(movieDetails);

        PopularMoviesRobot
                .create()
                .launchPopularMovies(rule)
                .selectPosterAtPosition(0)
                .toRobot(PosterDetailsRobot.class)
                .checkMovieTitleIsDisplayed(MOVIE_TITLE)
                .checkMovieDescriptionIsDisplayed(MOVIE_DESCRIPTION);
    }

    private ApiMoviePoster createApiMoviePoster(long movieId, String posterPath) {
        return new ApiMoviePoster(movieId, posterPath);
    }

    private ApiMovieDetails createMovieDetails(long movieId, String movieTitle, String movieOverview, String posterPath, String releaseDate) {
        ApiMovieDetails apiMovieDetails = new ApiMovieDetails();
        apiMovieDetails.originalTitle = movieTitle;
        apiMovieDetails.movieId = movieId;
        apiMovieDetails.overview = movieOverview;
        apiMovieDetails.posterPath = posterPath;
        apiMovieDetails.releaseDate = releaseDate;
        return apiMovieDetails;
    }
}
