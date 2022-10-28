package com.epam.cinema.spring.service;

import com.epam.cinema.spring.enity.*;
import com.epam.cinema.spring.enity.Seat;
import com.epam.cinema.spring.repository.SeatRepository;
import com.epam.cinema.spring.service.implementation.SeatReservedService;
import com.epam.cinema.spring.service.implementation.SeatService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class SeatServiceTest {
    @Mock
    private SeatRepository seatRepository;

    @Mock
    private SeatReservedService seatReservedService;

    @InjectMocks
    private SeatService seatService;

    private Seat seat;

    private Auditorium auditorium;

    @BeforeEach
    public void setup() {
        auditorium = Auditorium.builder()
                .id(1)
                .build();

        seat = Seat.builder()
                .id(1)
                .auditorium(auditorium)
                .seatNumber(1)
                .seatRow(1)
                .build();
    }

    @DisplayName("JUnit test for findSeat method")
    @Test
    public void givenSeatObject_whenFindSeatByID_thenReturnSeatObject() {
        given(seatRepository.findById(seat.getId()))
                .willReturn(Optional.of(seat));

        Optional<Seat> findSeat = seatService.findSeatById(seat.getId());
        assertThat(findSeat).isPresent();
        assertThat(findSeat.get()).isInstanceOf(Seat.class);
    }

    @DisplayName("JUnit test for findAllSeat method")
    @Test
    public void givenSeatObject_whenFindAllSeat_thenReturnListOfSeatObject() {
        Seat anotherSeat = Seat.builder()
                .id(2)
                .auditorium(auditorium)
                .seatNumber(2)
                .seatRow(1)
                .build();

        given(seatService.findAllSeats(1)).willReturn(List.of(seat, anotherSeat));

        List<Seat> seat = seatService.findAllSeats(1);
        assertThat(seat).isNotNull();
        assertThat(seat.size()).isEqualTo(2);
    }

    @DisplayName("JUnit test for findAllSeat method")
    @Test
    public void givenSeatObject_whenFindAllSeatByAuditoriumId_thenReturnListOfSeatObject() {
        Seat anotherSeat = Seat.builder()
                .id(2)
                .auditorium(auditorium)
                .seatNumber(2)
                .seatRow(1)
                .build();

        given(seatRepository.findByAuditorium_Id(seat.getAuditorium().getId())).willReturn(List.of(seat, anotherSeat));

        List<Seat> seatList = seatService.findAllSeatsByAuditoriumId(seat.getAuditorium().getId());
        assertThat(seatList).isNotNull();
        assertThat(seatList.size()).isEqualTo(2);
    }

    @DisplayName("JUnit test for findAllSeat method")
    @Test
    public void givenSeatObject_whenFindAllAvailableSeatsByAuditoriumIdAndScreeningId_thenReturnListOfSeatObject() {
        Integer screeningId = 1;
        Seat anotherSeat = Seat.builder()
                .id(2)
                .auditorium(auditorium)
                .seatNumber(2)
                .seatRow(1)
                .build();

        SeatReserved seatReserved = SeatReserved.builder()
                .seat(anotherSeat)
                .build();

        List<Seat> seats = new ArrayList<>();
        seats.add(seat);
        seats.add(anotherSeat);

        given(seatReservedService.findAllSeatReservedByScreeningId(screeningId)).willReturn(List.of(seatReserved));
        given(seatRepository.findByAuditorium_Id(seat.getAuditorium().getId())).willReturn(seats);

        Map<Integer, Map<Seat, Boolean>> seatList = seatService.findAllAvailableSeatsByAuditoriumIdAndScreeningId(seat.getAuditorium().getId(), screeningId);
        assertThat(seatList).isNotNull();
        assertThat(seatList.size()).isEqualTo(1);
    }

    @DisplayName("JUnit test for findAllRowAndSeat method")
    @Test
    public void givenSeatObject_whenFindAllRowsAndSeats_thenReturnMapOfIntegerAndLongObject() {
        Seat anotherSeat = Seat.builder()
                .id(2)
                .auditorium(auditorium)
                .seatNumber(2)
                .seatRow(1)
                .build();

        given(seatService.findAllSeats(1)).willReturn(List.of(seat, anotherSeat));

        Map<Integer, Long> auditoriumMap= seatService.findAllRowsAndSeatsByAuditoriumId(1);
        assertThat(auditoriumMap).isNotNull();
        assertThat(auditoriumMap.size()).isEqualTo(1);
    }

    @DisplayName("JUnit test for findAllRowAndSeat method")
    @Test
    public void givenSeatObject_whenFindAllRowsAndSeats_thenReturnNull() {
        given(seatService.findAllSeats(1)).willReturn(List.of());

        Map<Integer, Long> auditoriumMap = seatService.findAllRowsAndSeatsByAuditoriumId(1);
        assertThat(auditoriumMap).isNull();
    }

    @DisplayName("JUnit test for addSeat method")
    @Test
    public void givenSeatObject_whenAddSeat_thenReturnSeatObject() {
        given(seatRepository.save(seat)).willReturn(seat);

        Seat savedSeat = seatService.addSeat(seat);
        assertThat(savedSeat).isNotNull();
        assertThat(savedSeat).isInstanceOf(Seat.class);
    }

    @DisplayName("JUnit test for deleteSeat method")
    @Test
    public void givenSeatObject_whenDeleteSeat_thenVoid() {
        willDoNothing().given(seatRepository).delete(seat);
        seatService.deleteSeat(seat);
        verify(seatRepository, times(1)).delete(seat);
    }
}
