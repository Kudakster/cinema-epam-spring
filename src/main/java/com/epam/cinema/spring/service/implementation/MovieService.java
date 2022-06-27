package com.epam.cinema.spring.service.implementation;

import com.epam.cinema.spring.enity.Movie;
import com.epam.cinema.spring.repository.MovieRepository;
import com.epam.cinema.spring.service.IMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService implements IMovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Override
    public Optional<Movie> findMovieById(Integer id) {
        return movieRepository.findById(id);
    }

    @Override
    public Optional<Movie> findMovieByName(String name) {
        return movieRepository.findByMovieName(name);
    }

    @Override
    public List<Movie> findMoviesByNamePattern(String name) {
        return null;
    }

    @Override
    public List<Movie> findMoviesByPagination(Pageable pageable) {
        return movieRepository.findByOrderByIdDesc(pageable);
    }

    @Override
    public List<Movie> findAllMovies() {
        return movieRepository.findAll();
    }

    @Override
    public Movie addMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    @Override
    public Movie updateMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    @Override
    public boolean isMovieExist(String name) {
        return movieRepository.existsByMovieName(name);
    }

    @Override
    public long countAllMovies() {
        return movieRepository.count();
    }

    @Override
    public void deleteMovie(Movie movie) {
        movieRepository.delete(movie);
    }
}
