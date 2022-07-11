package com.epam.cinema.spring.service;

import com.epam.cinema.spring.enity.Seat;

import java.util.List;
import java.util.Map;
import java.util.Optional;


/**
 * The interface Seat service.
 */
public interface ISeatService {

    /**
     * Find seat by id optional.
     *
     * @param id the id
     * @return the optional
     */
    Optional<Seat> findSeatById(Integer id);

    /**
     * Find all seats list.
     *
     * @return the list
     */
    List<Seat> findAllSeats();

    /**
     * Find all seats by auditorium id list.
     *
     * @param id the id
     * @return the list
     */
    List<Seat> findAllSeatsByAuditoriumId(Integer id);

    /**
     * Find all available seats by auditorium id and screening id list.
     *
     * @param auditoriumId the auditorium id
     * @param screeningId  the screening id
     * @return the list
     */
    List<Seat> findAllAvailableSeatsByAuditoriumIdAndScreeningId(Integer auditoriumId, Integer screeningId);

    /**
     * Find all rows and seats map.
     *
     * @return the map
     */
    Map<Integer, Long> findAllRowsAndSeats();

    /**
     * Add seat seat.
     *
     * @param movie the movie
     * @return the seat
     */
    Seat addSeat(Seat movie);

    /**
     * Add seats list.
     *
     * @param seatList the seat list
     * @return the list
     */
    List<Seat> addSeats(List<Seat> seatList);

    /**
     * Update seats list.
     *
     * @param seatList the seat list
     * @return the list
     */
    List<Seat> updateSeats(List<Seat> seatList);

    /**
     * Delete seat.
     *
     * @param movie the movie
     */
    void deleteSeat(Seat movie);

    /**
     * Delete seats.
     *
     * @param seatList the seat list
     */
    void deleteSeats(List<Seat> seatList);
}
