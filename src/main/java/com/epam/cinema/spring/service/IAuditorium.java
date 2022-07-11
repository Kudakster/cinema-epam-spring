package com.epam.cinema.spring.service;

import com.epam.cinema.spring.enity.Auditorium;

import java.util.List;
import java.util.Optional;

/**
 * The interface Auditorium.
 */
public interface IAuditorium {

    /**
     * Find auditorium by id return optional.
     *
     * @param id the id of Auditorium
     * @return the optional
     */
    Optional<Auditorium> findAuditoriumById(Integer id);


    /**
     * Find auditorium by name return optional.
     *
     * @param name the name of Auditorium
     * @return the optional
     */
    Optional<Auditorium> findAuditoriumByName(String name);

    /**
     * Find all auditoriums list.
     *
     * @return the list of all Auditorium
     */
    List<Auditorium> findAllAuditoriums();

    /**
     * Adds auditorium and returns auditorium.
     *
     * @param auditorium not null
     * @return the auditorium
     */
    Auditorium addAuditorium(Auditorium auditorium);

    /**
     * Updates auditorium and returns updated auditorium.
     *
     * @param auditorium not null
     * @return the auditorium
     */
    Auditorium updateAuditorium(Auditorium auditorium);

    /**
     * Delete auditorium.
     *
     * @param auditorium not null
     */
    void deleteAuditorium(Auditorium auditorium);
}

