package com.chipcerio.moovy.features.master_list

import com.chipcerio.moovy.data.Movie
import com.chipcerio.moovy.data.source.repository.PopularMoviesRepository
import io.reactivex.Observable
import io.reactivex.observers.TestObserver
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnit

class PopularMoviesViewModelTest {

    @Rule @JvmField
    val mockitoRule = MockitoJUnit.rule()

    @Mock
    private lateinit var repository: PopularMoviesRepository

    private lateinit var viewModel: PopularMoviesViewModel

    private lateinit var displayableMoviesSubscriber: TestObserver<MutableList<DisplayableMovie>>

    private lateinit var moviesSubscriber: TestObserver<MutableList<Movie>>

    private lateinit var MOVIES: MutableList<Movie>

    private lateinit var DISPLAYABLE_MOVIES: MutableList<DisplayableMovie>

    private val PAGE_1 = 1

    @Before
    fun setup() {

        viewModel = PopularMoviesViewModel(repository)

        MOVIES = arrayListOf(
            Movie(
                id = 0,
                title = "The Dark Knight",
                posterPath = "/xsw6zGa.jpg",
                releaseDate = "2008-07-17",
                overview = "With the help of allies Lt. Jim Gordon and DA Harvey Dent, Batman has been able to keep a tight lid on crime in Gotham City.",
                voteCount = 100,
                video = true,
                voteAverage = 99.99,
                popularity = 99.99,
                originalLanguage = "en",
                originalTitle = "The Dark Knight",
                genreIds = arrayListOf(1, 2, 3, 4, 5),
                backdropPath = "/xsw6zGa.jpg",
                adult = false
            ),
            Movie(
                id = 1,
                title = "The Dark Knight Rises",
                posterPath = "/kJAww2i.jpg",
                releaseDate = "2008-07-17",
                overview = "It has been eight years since Batman, in collusion with Commissioner Gordon, vanished into the night.",
                voteCount = 100,
                video = true,
                voteAverage = 99.99,
                popularity = 99.99,
                originalLanguage = "en",
                originalTitle = "The Dark Knight Rises",
                genreIds = arrayListOf(1, 2, 3, 4, 5),
                backdropPath = "/kJAww2i.jpg",
                adult = false
            )
        )

        DISPLAYABLE_MOVIES = arrayListOf(
            DisplayableMovie(
                id = 0,
                title = "The Dark Knight",
                imageUrl = "https://i.imgur.com/xsw6zGa.jpg",
                releaseDate = "2008-07-17",
                overview = "With the help of allies Lt. Jim Gordon and DA Harvey Dent, Batman has been able to keep a tight lid on crime in Gotham City."
            ),
            DisplayableMovie(
                id = 1,
                title = "The Dark Knight Rises",
                imageUrl = "https://i.imgur.com/kJAww2i.jpg",
                releaseDate = "2012-07-19",
                overview = "It has been eight years since Batman, in collusion with Commissioner Gordon, vanished into the night."
            )
        )

        displayableMoviesSubscriber = TestObserver.create()
        moviesSubscriber = TestObserver.create()
    }

    @Test
    fun Should_LoadMovies_When_Requested() {
        `when`(repository.getPopularMovies(PAGE_1))
            .thenReturn(Observable.just(MOVIES))

        repository.getPopularMovies(PAGE_1)
            .subscribe(moviesSubscriber)

        moviesSubscriber.assertSubscribed()
        moviesSubscriber.assertResult(MOVIES)
        moviesSubscriber.assertComplete()
        // https://stackoverflow.com/a/3623574/1076574
    }
}