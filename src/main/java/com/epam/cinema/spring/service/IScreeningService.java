package com.epam.cinema.spring.service;

import com.epam.cinema.spring.enity.Screening;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;


/**
 * The interface Screening service.
 */
public interface IScreeningService {
    /**
     * Find screening by id.
     *
     * @param id the id
     * @return the optional
     */
    Optional<Screening> findScreeningById(Integer id);

    /**
     * Find screening by date and auditorium id.
     *
     * @param date the date
     * @param id   the id
     * @return the optional
     */
    Optional<Screening>  findScreeningByDateAndAuditoriumID(LocalDate date, Integer id);

    /**
     * Find all screenings list.
     *
     * @return the list
     */
    List<Screening> findAllScreenings();

    /**
     * Find screenings by date list.
     *
     * @param date the date
     * @return the list
     */
    List<Screening> findScreeningsByDate(LocalDate date);

    /**
     * Find screenings by date and time list.
     *
     * @param date      the date
     * @param time      the time
     * @param direction the direction
     * @param sortBy    the sort by
     * @return the list
     */
    List<Screening> findScreeningsByDateAndTime(LocalDate date, LocalTime time, Sort.Direction direction, String sortBy);

    /**
     * Add screening screening.
     *
     * @param screening the screening
     * @return the screening
     */
    Screening addScreening(Screening screening);

    /**
     * Update screening screening.
     *
     * @param screening the screening
     * @return the screening
     */
    Screening updateScreening(Screening screening);

    /**
     * Is screening exist by movie id boolean.
     *
     * @param id the id
     * @return the boolean
     */
    boolean isScreeningExistByMovieId(Integer id);

    /**
     * Is screening have tickets boolean.
     *
     * @param id the id
     * @return the boolean
     */
    boolean isScreeningHaveTickets(Integer id);

    /**
     * Delete screening.
     *
     * @param screening the screening
     */
    void deleteScreening(Screening screening);
}
