package com.epam.cinema.spring.repository;

import com.epam.cinema.spring.enity.SeatReserved;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SeatReservedRepository extends JpaRepository<SeatReserved, Integer> {
    Optional<SeatReserved> findBySeat_IdAndScreening_Id(Integer id, Integer id1);

    List<SeatReserved> findByScreening_Id(Integer id);

    long countByScreening_Id(Integer id);

    boolean existsBySeat_IdAndScreening_Id(Integer seatId, Integer screeningId);


}