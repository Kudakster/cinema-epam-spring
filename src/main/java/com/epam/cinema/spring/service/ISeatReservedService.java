package com.epam.cinema.spring.service;

import com.epam.cinema.spring.enity.SeatReserved;

import java.util.List;
import java.util.Optional;

public interface ISeatReservedService {

    Optional<SeatReserved> findSeatReservedById(Integer id);

    Optional<SeatReserved> findSeatReservedBySeatIdAndScreeningId(Integer seatID, Integer screeningID);

    List<SeatReserved> findAllSeatReserved();

    List<SeatReserved> findAllSeatReservedByScreeningId(Integer id);

    SeatReserved addSeatReserved(SeatReserved seatReserved);

    boolean existsBySeatIdAndScreeningId(Integer seatId, Integer screeningId);

    void deleteSeatReserved(SeatReserved seatReserved);
}
