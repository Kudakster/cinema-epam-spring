package com.epam.cinema.spring.service;

import com.epam.cinema.spring.enity.Ticket;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ITicketService {

    Optional<Ticket> findTicketByID(Integer id);

    List<Ticket> findAllTickets();

    List<Ticket> findTicketsByUserIDAndCurrentTime(Integer id);

    Ticket addTicket(Ticket ticket);

    long countTicketByDate(LocalDate localDate);

    void deleteTicket(Ticket ticket);
}
