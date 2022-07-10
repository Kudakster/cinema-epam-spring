package com.epam.cinema.spring.repository;

import com.epam.cinema.spring.enity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Integer> {

    Optional<Seat> findBySeatRowAndSeatNumber(Integer seatRow, Integer seatNumber);

    List<Seat> findByAuditorium_Id(Integer id);
}