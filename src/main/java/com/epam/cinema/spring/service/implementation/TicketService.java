package com.epam.cinema.spring.service.implementation;

import com.epam.cinema.spring.enity.Ticket;
import com.epam.cinema.spring.repository.TicketRepository;
import com.epam.cinema.spring.service.ITicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TicketService implements ITicketService {
    @Autowired
    private TicketRepository ticketRepository;

    @Override
    public Optional<Ticket> findTicketByID(Integer id) {
        return ticketRepository.findById(id);
    }

    @Override
    public List<Ticket> findAllTickets() {
        return ticketRepository.findAll();
    }

    @Override
    public List<Ticket> findTicketsByUserIDAndCurrentTime(Integer id) {
        return ticketRepository.findByUser_IdAndSeatReserved_Screening_ScreeningDateIsGreaterThanEqual(id, LocalDate.now());
    }

    @Override
    public Ticket addTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    @Override
    public long countTicketByDate(LocalDate localDate) {
        return ticketRepository.countBySeatReserved_Screening_ScreeningDateGreaterThanEqual(localDate);
    }

    @Override
    public void deleteTicket(Ticket ticket) {
        ticketRepository.delete(ticket);
    }
}
