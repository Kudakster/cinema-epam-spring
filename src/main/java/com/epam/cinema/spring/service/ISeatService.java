package com.epam.cinema.spring.service;

import com.epam.cinema.spring.enity.Seat;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ISeatService {

    Optional<Seat> findSeatById(Integer id);

    List<Seat> findAllSeats();

    List<Seat> findAllSeatsByAuditoriumId(Integer id);

    List<Seat> findAllAvailableSeatsByAuditoriumIdAndScreeningId(Integer auditoriumId, Integer screeningId);

    Map<Integer, Long> findAllRowsAndSeats();

    Seat addSeat(Seat movie);

    List<Seat> addSeats(List<Seat> seatList);

    List<Seat> updateSeats(List<Seat> seatList);

    void deleteSeat(Seat movie);

    void deleteSeats(List<Seat> seatList);
}
