package com.epam.cinema.spring.service;

import com.epam.cinema.spring.enity.Movie;
import com.epam.cinema.spring.repository.MovieRepository;
import com.epam.cinema.spring.service.implementation.MovieService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class MovieServiceTest {

    @Mock
    private MovieRepository movieRepository;

    @InjectMocks
    private MovieService movieService;

    private Movie movie;

    @BeforeEach
    public void setup() {
        movie = Movie.builder()
                .id(1)
                .movieName("Java")
                .build();
    }

    @DisplayName("JUnit test for findMovie method")
    @Test
    public void givenMovieObject_whenFindMovieByID_thenReturnMovieObject() {
        given(movieRepository.findById(movie.getId()))
                .willReturn(Optional.of(movie));

        Optional<Movie> findMovie = movieService.findMovieById(movie.getId());
        assertThat(findMovie).isPresent();
        assertThat(findMovie.get()).isInstanceOf(Movie.class);
    }

    @DisplayName("JUnit test for findMovie method")
    @Test
    public void givenMovieObject_whenFindMovieByName_thenReturnMovieObject() {
        given(movieRepository.findByMovieName(movie.getMovieName()))
                .willReturn(Optional.of(movie));

        Optional<Movie> findMovie = movieService.findMovieByName(movie.getMovieName());
        assertThat(findMovie).isPresent();
        assertThat(findMovie.get()).isInstanceOf(Movie.class);
    }

    @DisplayName("JUnit test for findAllMovie method")
    @Test
    public void givenMovieObject_whenFindAllMovie_thenReturnListOfMovieObject() {
        Movie anotherMovie = Movie.builder()
                .id(2)
                .movieName("Kotlin")
                .build();

        given(movieRepository.findAll()).willReturn(List.of(movie, anotherMovie));

        List<Movie> movies = movieService.findAllMovies();
        assertThat(movies).isNotNull();
        assertThat(movies.size()).isEqualTo(2);
    }

    @DisplayName("JUnit test for findAllMovie method")
    @Test
    public void givenMovieObject_whenFindAllMovieByPagination_thenReturnListOfMovieObject() {
        Movie anotherMovie = Movie.builder()
                .id(2)
                .movieName("Kotlin")
                .build();

        given(movieRepository.findByOrderByIdDesc(PageRequest.of(0, 2))).willReturn(List.of(movie, anotherMovie));

        List<Movie> movies = movieService.findMoviesByPagination(PageRequest.of(0, 2));
        assertThat(movies).isNotNull();
        assertThat(movies.size()).isEqualTo(2);
    }

    @DisplayName("JUnit test for saveMovie method")
    @Test
    public void givenMovieObject_whenAddMovie_thenReturnMovieObject() {
        given(movieRepository.save(movie)).willReturn(movie);

        Movie savedMovie = movieService.addMovie(movie);
        assertThat(savedMovie).isNotNull();
        assertThat(savedMovie).isInstanceOf(Movie.class);
    }

    @DisplayName("JUnit test for updateMovie method")
    @Test
    public void givenMovieObject_whenUpdateMovie_thenReturnUpdateMovieObject() {
        movie.setMovieCountry("India");

        given(movieRepository.save(movie)).willReturn(movie);

        Movie updateMovie = movieService.updateMovie(movie);
        assertThat(updateMovie).isNotNull();
        assertThat(updateMovie).isInstanceOf(Movie.class);
        assertThat(updateMovie.getMovieCountry()).isEqualTo("India");
    }

    @DisplayName("JUnit test for isMovieExist method")
    @Test
    public void givenMovieObject_whenIsMovieExist_thenReturnBooleanObject() {
        given(movieRepository.existsByMovieName(movie.getMovieName())).willReturn(true);

        boolean result = movieService.isMovieExist(movie.getMovieName());
        assertThat(result).isTrue();
    }

    @DisplayName("JUnit test for countAllMovie method")
    @Test
    public void givenMovieObject_whenCountAllMovie_thenReturnLongObject() {
        given(movieRepository.count()).willReturn(2L);

        Long countOfMovies = movieService.countAllMovies();
        assertThat(countOfMovies).isNotNull();
        assertThat(countOfMovies).isEqualTo(2L);
    }



    @DisplayName("JUnit test for deleteMovie method")
    @Test
    public void givenMovieObject_whenDeleteMovie_thenVoid() {
        willDoNothing().given(movieRepository).delete(movie);
        movieService.deleteMovie(movie);
        verify(movieRepository, times(1)).delete(movie);
    }
}
