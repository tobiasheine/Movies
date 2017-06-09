package com.tobi.movies.posterdetails

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import com.tobi.movies.Converter
import org.joda.time.LocalDate
import org.junit.Before
import org.junit.Test
import rx.Observable
import rx.observers.TestSubscriber

class MovieDetailsRepositoryTest {

    private lateinit var repository: MovieDetailsRepository

    private val apiDataSource: MovieDetailsApiDatasource = mock()
    private val converter: Converter<ApiMovieDetails, MovieDetails> = ApiMovieDetailsConverter()

    @Before
    fun setUp() {
        repository = MovieDetailsRepository(apiDataSource, converter)
    }

    @Test
    fun shouldReturnMovieDetailsForId() {
        val movieId = 1L
        val imagePath = "/path"
        val overview = "overview"
        val title = "title"
        val releaseDate = "2001-01-01"
        val apiMovieDetails = createMovieDetails(movieId, imagePath, overview, title, releaseDate)
        whenever(apiDataSource.getMovieDetails(movieId)).thenReturn(Observable.just(apiMovieDetails))

        val movieDetails = repository.getMovieDetails(movieId)
        val testSubscriber: TestSubscriber<MovieDetails> = TestSubscriber()
        movieDetails.subscribe(testSubscriber)

        val expectedMovieDetails = MovieDetails(
                overview,
                movieId,
                title,
                "http://image.tmdb.org/t/p/w500/${imagePath.substring(1, imagePath.length)}",
                LocalDate(releaseDate))

        testSubscriber.assertValue(expectedMovieDetails)
    }

    private fun createMovieDetails(movieId: Long, imagePath: String, overview: String, title: String, releaseDate: String): ApiMovieDetails {
        val apiMovieDetails = ApiMovieDetails()
        apiMovieDetails.movieId = movieId
        apiMovieDetails.posterPath = imagePath
        apiMovieDetails.overview = overview
        apiMovieDetails.originalTitle = title
        apiMovieDetails.releaseDate = releaseDate

        return apiMovieDetails
    }
}



