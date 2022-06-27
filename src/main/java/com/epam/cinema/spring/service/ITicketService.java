package com.epam.cinema.spring.service;

import com.epam.cinema.spring.enity.Ticket;

import java.util.List;
import java.util.Optional;

public interface ITicketService {

    Optional<Ticket> findTicketByID(Integer id);

    List<Ticket> findAllTickets();

    List<Ticket> findTicketsByUserID(Integer id);

    List<Ticket> findTicketsByUserIDAndCurrentTime(Integer id);

    Ticket addTicket(Ticket ticket);

    void deleteTicket(Ticket ticket);
}
