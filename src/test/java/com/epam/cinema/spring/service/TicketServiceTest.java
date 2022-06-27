package com.epam.cinema.spring.service;

import com.epam.cinema.spring.enity.Ticket;
import com.epam.cinema.spring.enity.User;
import com.epam.cinema.spring.repository.TicketRepository;
import com.epam.cinema.spring.service.implementation.TicketService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class TicketServiceTest {

    @Mock
    private TicketRepository ticketRepository;

    @InjectMocks
    private TicketService ticketService;

    private Ticket ticket;

    private User user;

    @BeforeEach
    public void setup() {
        user = User.builder()
                .id(1)
                .build();

        ticket = Ticket.builder()
                .id(1)
                .user(user)
                .build();
    }

    @DisplayName("JUnit test for saveTicket method")
    @Test
    public void givenTicketObject_whenAddTicket_thenReturnTicketObject() {
        given(ticketRepository.save(ticket)).willReturn(ticket);

        Ticket savedTicket = ticketService.addTicket(ticket);
        assertThat(savedTicket).isNotNull();
        assertThat(savedTicket).isInstanceOf(Ticket.class);
    }

    @DisplayName("JUnit test for findTicket method")
    @Test
    public void givenTicketObject_whenFindTicketByID_thenReturnTicketObject() {
        given(ticketRepository.findById(Math.toIntExact(ticket.getId())))
                .willReturn(Optional.of(ticket));

        Optional<Ticket> findTicket = ticketService.findTicketByID(ticket.getId());
        assertThat(findTicket).isPresent();
        assertThat(findTicket.get()).isInstanceOf(Ticket.class);
    }

    @DisplayName("JUnit test for findAllTicket method")
    @Test
    public void givenTicketObject_whenFindAllTicket_thenReturnTicketObject() {
        Ticket anotherTicket = Ticket.builder()
                .id(2)
                .user(user)
                .build();

        given(ticketRepository.findAll()).willReturn(List.of(ticket, anotherTicket));

        List<Ticket> tickets = ticketService.findAllTickets();
        assertThat(tickets).isNotNull();
        assertThat(tickets.size()).isEqualTo(2);
    }

    @DisplayName("JUnit test for findAllTicket method")
    @Test
    public void givenTicketObject_whenFindAllTicketByUserId_thenReturnTicketObject() {
        Ticket anotherTicket = Ticket.builder()
                .id(2)
                .user(user)
                .build();

        given(ticketRepository.findByUser_IdAndSeatReserved_Screening_ScreeningDateIsGreaterThanEqual(ticket.getUser().getId(), LocalDate.now()))
                .willReturn(List.of(ticket, anotherTicket));

        List<Ticket> tickets = ticketService.findTicketsByUserIDAndCurrentTime(ticket.getUser().getId());
        assertThat(tickets).isNotNull();
        assertThat(tickets.size()).isEqualTo(2);
    }

    @DisplayName("JUnit test for deleteTicket method")
    @Test
    public void givenTicketObject_whenDeleteTicket_thenVoid() {
        willDoNothing().given(ticketRepository).delete(ticket);
        ticketService.deleteTicket(ticket);
        verify(ticketRepository, times(1)).delete(ticket);
    }
}
