package com.epam.cinema.spring.service;

import com.epam.cinema.spring.enity.SeatReserved;
import com.epam.cinema.spring.enity.Screening;
import com.epam.cinema.spring.enity.Seat;
import com.epam.cinema.spring.repository.SeatReservedRepository;
import com.epam.cinema.spring.service.implementation.SeatReservedService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class SeatReservedServiceTest {
    @Mock
    private SeatReservedRepository seatReservedRepository;

    @InjectMocks
    private SeatReservedService seatReservedService;

    private SeatReserved seatReserved;

    private Seat seat;

    private Screening screening;

    @BeforeEach
    public void setup() {
        seat = Seat.builder()
                .id(1)
                .build();

        screening = Screening.builder()
                .id(1)
                .build();

        seatReserved = SeatReserved.builder()
                .id(1)
                .seat(seat)
                .screening(screening)
                .build();
    }

    @DisplayName("JUnit test for findSeatReserved method")
    @Test
    public void givenSeatReservedObject_whenFindSeatReservedByID_thenReturnSeatReservedObject() {
        given(seatReservedRepository.findById(seatReserved.getId()))
                .willReturn(Optional.of(seatReserved));

        Optional<SeatReserved> findSeatReserved = seatReservedService.findSeatReservedById(seatReserved.getId());
        assertThat(findSeatReserved).isPresent();
        assertThat(findSeatReserved.get()).isInstanceOf(SeatReserved.class);
    }

    @DisplayName("JUnit test for findSeatReserved method")
    @Test
    public void givenSeatReservedObject_whenFindSeatReservedByName_thenReturnSeatReservedObject() {
        given(seatReservedRepository.findBySeat_IdAndScreening_Id(seatReserved.getSeat().getId(), seatReserved.getScreening().getId()))
                .willReturn(Optional.of(seatReserved));

        Optional<SeatReserved> findSeatReserved = seatReservedService.findSeatReservedBySeatIdAndScreeningId(seatReserved.getSeat().getId(), seatReserved.getScreening().getId());
        assertThat(findSeatReserved).isPresent();
        assertThat(findSeatReserved.get()).isInstanceOf(SeatReserved.class);
    }

    @DisplayName("JUnit test for findAllSeatReserved method")
    @Test
    public void givenSeatReservedObject_whenFindAllSeatReserved_thenReturnListOfSeatReservedObject() {
        seat.setId(2);

        SeatReserved anotherSeatReserved = SeatReserved.builder()
                .id(2)
                .seat(seat)
                .screening(screening)
                .build();

        given(seatReservedRepository.findAll()).willReturn(List.of(seatReserved, anotherSeatReserved));

        List<SeatReserved> seatReserved = seatReservedService.findAllSeatReserved();
        assertThat(seatReserved).isNotNull();
        assertThat(seatReserved.size()).isEqualTo(2);
    }

    @DisplayName("JUnit test for findAllSeatReserved method")
    @Test
    public void givenSeatReservedObject_whenFindAllSeatReservedByScreeningId_thenReturnListOfSeatReservedObject() {
        SeatReserved anotherSeatReserved = SeatReserved.builder()
                .id(2)
                .seat(seat)
                .screening(screening)
                .build();

        given(seatReservedRepository.findByScreening_Id(seatReserved.getScreening().getId())).willReturn(List.of(seatReserved, anotherSeatReserved));

        List<SeatReserved> seatReservedList = seatReservedService.findAllSeatReservedByScreeningId(seatReserved.getScreening().getId());
        assertThat(seatReservedList).isNotNull();
        assertThat(seatReservedList.size()).isEqualTo(2);
    }

    @DisplayName("JUnit test for addSeatReserved method")
    @Test
    public void givenSeatReservedObject_whenAddSeatReserved_thenReturnSeatReservedObject() {
        given(seatReservedRepository.save(seatReserved)).willReturn(seatReserved);

        SeatReserved savedSeatReserved = seatReservedService.addSeatReserved(seatReserved);
        assertThat(savedSeatReserved).isNotNull();
        assertThat(savedSeatReserved).isInstanceOf(SeatReserved.class);
    }

    @DisplayName("JUnit test for isSeatReservedExist method")
    @Test
    public void givenSeatReservedObject_whenIsSeatReservedExist_thenReturnBooleanObject() {
        given(seatReservedRepository.existsBySeat_IdAndScreening_Id(seatReserved.getSeat().getId(), seatReserved.getScreening().getId())).willReturn(true);

        boolean result = seatReservedService.existsBySeatIdAndScreeningId(seatReserved.getSeat().getId(), seatReserved.getScreening().getId());
        assertThat(result).isTrue();
    }

    @DisplayName("JUnit test for deleteSeatReserved method")
    @Test
    public void givenSeatReservedObject_whenDeleteSeatReserved_thenVoid() {
        willDoNothing().given(seatReservedRepository).delete(seatReserved);
        seatReservedService.deleteSeatReserved(seatReserved);
        verify(seatReservedRepository, times(1)).delete(seatReserved);
    }
}
