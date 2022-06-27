package com.epam.cinema.spring.repository;

import com.epam.cinema.spring.enity.Movie;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {
    Optional<Movie> findByMovieName(String movieName);

    List<Movie> findByOrderByIdDesc(Pageable pageable);

    boolean existsByMovieName(String movieName);


}