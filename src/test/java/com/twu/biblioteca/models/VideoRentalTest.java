package com.twu.biblioteca.models;

import com.twu.biblioteca.HelperIO;
import com.twu.biblioteca.menus.Menu;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

public class VideoRentalTest {

    List<Movie> movies;
    VideoRental videoRental;
    private HelperIO helperIO;

    @Before
    public void setUp() {
        helperIO = new HelperIO();
        movies = new ArrayList<Movie>();
        movies.add(new Movie("Movie 1", "Director 1", 2000, 9.2));
        movies.add(new Movie( "Movie 2", "Director 2", 2010));
        videoRental = new VideoRental(movies);
    }

    @After
    public void tearDown() {
        helperIO.restoreIO();
    }

    @Test
    public void shouldHaveAListOfMovies() {
        assertThat(videoRental.getProducts(), is(movies));
    }

    @Test
    public void shouldReturnMovieListTableHeaderWhenGetMovieListTablePrintableIsCalled() {
        String movieListTablePrintable = videoRental.getMovieListTablePrintable();

        assertThat(movieListTablePrintable, containsString("List of Movies"));
        assertThat(movieListTablePrintable, containsString("ID"));
        assertThat(movieListTablePrintable, containsString("TITLE"));
        assertThat(movieListTablePrintable, containsString("DIRECTOR"));
        assertThat(movieListTablePrintable, containsString("YEAR"));
        assertThat(movieListTablePrintable, containsString("RATING"));
    }

    @Test
    public void shouldReturnMovieListTableBodyWhenGetMovieListTablePrintableIsCalled() {
        String movieListTablePrintable = videoRental.getMovieListTablePrintable();
        for(Movie movie : movies) {
            assertThat(movieListTablePrintable, containsString(String.valueOf(movie.getId())));
            assertThat(movieListTablePrintable, containsString(movie.getTitle()));
            assertThat(movieListTablePrintable, containsString(movie.getDirector()));
            assertThat(movieListTablePrintable, containsString(String.valueOf(movie.getYear())));
            assertThat(movieListTablePrintable, containsString(String.valueOf(movie.getRatingPrintable())));
        }
    }


    @Test
    public void shouldNotListCheckedOutMoviesWhenGetMovieListTablePrintableIsCalled() {
        UUID movieId = videoRental.getProducts().get(0).getId();
        videoRental.checkoutProductById(movieId);

        String movieListTablePrintable = videoRental.getMovieListTablePrintable();

        assertThat(movieListTablePrintable, not(containsString(String.valueOf(movieId))));
    }


    @Test
    public void shouldPrintListMoviesWhenShowMovieListIsCalled() {
        videoRental.setMenu(mock(Menu.class));
        videoRental.showMovieListTablePrintable();
        assertThat(helperIO.getOutContent(), containsString(videoRental.getMovieListTablePrintable()));
    }
}
