package com.epam.cinema.spring.service;

import com.epam.cinema.spring.enity.Movie;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IMovieService {

    Optional<Movie> findMovieById(Integer id);

    Optional<Movie> findMovieByName(String name);

    List<Movie> findMoviesByNamePattern(String name);

    List<Movie> findMoviesByPagination(Pageable pageable);

    List<Movie> findAllMovies();

    Movie addMovie(Movie movie);

    Movie updateMovie(Movie movie);

    boolean isMovieExist(String name);

    long countAllMovies();

    void deleteMovie(Movie movie);
}
