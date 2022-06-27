package com.epam.cinema.spring.service;

import com.epam.cinema.spring.enity.Auditorium;

import java.util.List;
import java.util.Optional;

public interface IAuditorium {
    Optional<Auditorium> findAuditoriumById(Integer id);

    Optional<Auditorium> findAuditoriumByName(String name);

    List<Auditorium> findAllAuditoriums();

    Auditorium addAuditorium(Auditorium auditorium);

    Auditorium updateAuditorium(Auditorium auditorium);

    void deleteAuditorium(Auditorium auditorium);
}

