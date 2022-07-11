package com.epam.cinema.spring.service;

import com.epam.cinema.spring.enity.SeatReserved;

import java.util.List;
import java.util.Optional;


/**
 * The interface Seat reserved service.
 */
public interface ISeatReservedService {

    /**
     * Find seat reserved by id optional.
     *
     * @param id the id
     * @return the optional
     */
    Optional<SeatReserved> findSeatReservedById(Integer id);

    /**
     * Find seat reserved by seat id and screening id optional.
     *
     * @param seatID      the seat id
     * @param screeningID the screening id
     * @return the optional
     */
    Optional<SeatReserved> findSeatReservedBySeatIdAndScreeningId(Integer seatID, Integer screeningID);

    /**
     * Find all seat reserved list.
     *
     * @return the list
     */
    List<SeatReserved> findAllSeatReserved();

    /**
     * Find all seat reserved by screening id list.
     *
     * @param id the id
     * @return the list
     */
    List<SeatReserved> findAllSeatReservedByScreeningId(Integer id);

    /**
     * Add seat reserved seat reserved.
     *
     * @param seatReserved the seat reserved
     * @return the seat reserved
     */
    SeatReserved addSeatReserved(SeatReserved seatReserved);

    /**
     * Exists by seat id and screening id boolean.
     *
     * @param seatId      the seat id
     * @param screeningId the screening id
     * @return the boolean
     */
    boolean existsBySeatIdAndScreeningId(Integer seatId, Integer screeningId);

    /**
     * Delete seat reserved.
     *
     * @param seatReserved the seat reserved
     */
    void deleteSeatReserved(SeatReserved seatReserved);
}
