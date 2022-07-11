package com.epam.cinema.spring.service;

import com.epam.cinema.spring.enity.Ticket;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


/**
 * The interface Ticket service.
 */
public interface ITicketService {

    /**
     * Find ticket by id optional.
     *
     * @param id the id
     * @return the optional
     */
    Optional<Ticket> findTicketByID(Integer id);

    /**
     * Find all tickets list.
     *
     * @return the list
     */
    List<Ticket> findAllTickets();

    /**
     * Find tickets by user id and current time list.
     *
     * @param id the id
     * @return the list
     */
    List<Ticket> findTicketsByUserIDAndCurrentTime(Integer id);

    /**
     * Add ticket ticket.
     *
     * @param ticket the ticket
     * @return the ticket
     */
    Ticket addTicket(Ticket ticket);

    /**
     * Count ticket by date long.
     *
     * @param localDate the local date
     * @return the long
     */
    long countTicketByDate(LocalDate localDate);

    /**
     * Delete ticket.
     *
     * @param ticket the ticket
     */
    void deleteTicket(Ticket ticket);
}
