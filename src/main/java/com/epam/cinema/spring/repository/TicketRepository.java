package com.epam.cinema.spring.repository;

import com.epam.cinema.spring.enity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {
    List<Ticket> findByUser_Id(Integer id);

    List<Ticket> findByUser_IdAndSeatReserved_Screening_ScreeningDateIsGreaterThanEqual(Integer id, LocalDate screeningDate);

    boolean existsBySeatReserved_Screening_Id(Integer id);

}