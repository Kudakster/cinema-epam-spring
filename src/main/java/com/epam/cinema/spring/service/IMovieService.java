package com.epam.cinema.spring.service;

import com.epam.cinema.spring.enity.Movie;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * The interface Movie service.
 */
public interface IMovieService {

    /**
     * Find movie by id and return optional.
     *
     * @param id the id of Movie
     * @return Optional<Movie>
     */
    Optional<Movie> findMovieById(Integer id);

    /**
     * Find movie by name and return optional.
     *
     * @param name the name of Movie
     * @return Optional<Movie>
     */
    Optional<Movie> findMovieByName(String name);

    /**
     * Find movies by pageable and return list of Movie.
     *
     * @param pageable the pageable
     * @return the list
     */
    List<Movie> findMoviesByPagination(Pageable pageable);

    /**
     * Find all movies list.
     *
     * @return the list
     */
    List<Movie> findAllMovies();

    /**
     * Add movie movie.
     *
     * @param movie the movie
     * @return the movie
     */
    Movie addMovie(Movie movie);

    /**
     * Update movie movie.
     *
     * @param movie the movie
     * @return the movie
     */
    Movie updateMovie(Movie movie);

    /**
     * Check if movie already exist, if exist return true else false.
     *
     * @param name the name of movie
     * @return the boolean
     */
    boolean isMovieExist(String name);

    /**
     * Count all movies in db.
     *
     * @return the long
     */
    long countAllMovies();

    /**
     * Delete movie.
     *
     * @param movie not null
     */
    void deleteMovie(Movie movie);
}
