package com.epam.cinema.spring.repository;

import com.epam.cinema.spring.enity.Auditorium;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuditoriumRepository extends JpaRepository<Auditorium, Integer> {
    Optional<Auditorium> findByAuditoriumName(String auditoriumName);


}