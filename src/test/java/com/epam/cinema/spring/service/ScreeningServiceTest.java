package com.epam.cinema.spring.service;

import com.epam.cinema.spring.enity.Auditorium;
import com.epam.cinema.spring.enity.Movie;
import com.epam.cinema.spring.enity.Screening;
import com.epam.cinema.spring.repository.ScreeningRepository;
import com.epam.cinema.spring.service.implementation.ScreeningService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class ScreeningServiceTest {
    @Mock
    private ScreeningRepository screeningRepository;

    @InjectMocks
    private ScreeningService screeningService;

    private Screening screening;

    private Auditorium auditorium;

    private Movie movie;

    @BeforeEach
    public void setup() {
        auditorium = new Auditorium(1, "screening");
        movie = new Movie();

        screening = Screening.builder()
                .id(1)
                .auditorium(auditorium)
                .movie(movie)
                .screeningDate(LocalDate.EPOCH)
                .screeningStartTime(LocalTime.MIN)
                .build();
    }

    @DisplayName("JUnit test for findScreening method")
    @Test
    public void givenScreeningObject_whenFindScreeningByID_thenReturnScreeningObject() {
        given(screeningRepository.findById(screening.getId()))
                .willReturn(Optional.of(screening));

        Optional<Screening> findScreening = screeningService.findScreeningById(screening.getId());
        assertThat(findScreening).isPresent();
        assertThat(findScreening.get()).isInstanceOf(Screening.class);
    }

    @DisplayName("JUnit test for findScreening method")
    @Test
    public void givenScreeningObject_whenFindScreeningByDateAndAuditoriumId_thenReturnScreeningObject() {
        given(screeningRepository.findByScreeningDateAndAuditorium_Id(screening.getScreeningDate(), screening.getAuditorium().getId()))
                .willReturn(Optional.of(screening));

        Optional<Screening> findScreening = screeningService.findScreeningByDateAndAuditoriumID(screening.getScreeningDate(), screening.getAuditorium().getId());
        assertThat(findScreening).isPresent();
        assertThat(findScreening.get()).isInstanceOf(Screening.class);
    }

    @DisplayName("JUnit test for findAllScreening method")
    @Test
    public void givenScreeningObject_whenFindAllScreening_thenReturnListOfScreeningObject() {
        Screening anotherScreening = Screening.builder()
                .id(2)
                .auditorium(auditorium)
                .screeningDate(LocalDate.EPOCH)
                .screeningStartTime(LocalTime.of(4, 0))
                .build();

        given(screeningRepository.findAll()).willReturn(List.of(screening, anotherScreening));

        List<Screening> screenings = screeningService.findAllScreenings();
        assertThat(screenings).isNotNull();
        assertThat(screenings.size()).isEqualTo(2);
    }

    @DisplayName("JUnit test for findAllScreening method")
    @Test
    public void givenScreeningObject_whenFindAllScreeningByDate_thenReturnListOfScreeningObject() {
        Screening anotherScreening = Screening.builder()
                .id(2)
                .auditorium(auditorium)
                .screeningDate(LocalDate.EPOCH)
                .screeningStartTime(LocalTime.of(4, 0))
                .build();

        given(screeningRepository.findByScreeningDateGreaterThanEqualOrderByScreeningDateAscScreeningStartTimeAsc(screening.getScreeningDate()))
                .willReturn(List.of(screening, anotherScreening));

        List<Screening> screenings = screeningService.findScreeningsByDate(screening.getScreeningDate());
        assertThat(screenings).isNotNull();
        assertThat(screenings.size()).isEqualTo(2);
    }

    @DisplayName("JUnit test for findAllScreening method")
    @Test
    public void givenScreeningObject_whenFindAllScreeningByDateAndTime_thenReturnListOfScreeningObject() {
        Screening anotherScreening = Screening.builder()
                .id(2)
                .auditorium(auditorium)
                .screeningDate(LocalDate.EPOCH)
                .screeningStartTime(LocalTime.of(4, 0))
                .build();

        given(screeningRepository.findByScreeningDateIsGreaterThanEqualAndScreeningStartTimeIsGreaterThanEqual(screening.getScreeningDate(), anotherScreening.getScreeningStartTime(), Sort.by(Sort.DEFAULT_DIRECTION, "screeningStartTime")))
                .willReturn(List.of(anotherScreening));

        List<Screening> screenings = screeningService.findScreeningsByDateAndTime(screening.getScreeningDate(), anotherScreening.getScreeningStartTime(), Sort.Direction.ASC, "screeningStartTime");
        assertThat(screenings).isNotNull();
        assertThat(screenings.size()).isEqualTo(1);
        assertThat(screenings.get(0)).isEqualTo(anotherScreening);
    }

    @DisplayName("JUnit test for getGroupedMaoScreening method")
    @Test
    public void givenScreeningObject_whenGetGroupedMaoScreeningByMovie_thenReturnMapOfMovieAndListOfScreeningObject() {
        Screening anotherScreening = Screening.builder()
                .id(2)
                .auditorium(auditorium)
                .movie(movie)
                .screeningDate(LocalDate.EPOCH)
                .screeningStartTime(LocalTime.of(4, 0))
                .build();

        given(screeningRepository.findByScreeningDateIsGreaterThanEqualAndScreeningStartTimeIsGreaterThanEqual(screening.getScreeningDate(), screening.getScreeningStartTime(), Sort.by(Sort.DEFAULT_DIRECTION, "screeningStartTime")))
                .willReturn(List.of(screening, anotherScreening));

        Map<Movie, List<Screening>> resultMap = screeningService.getGroupedMapScreeningByMovie(screening.getScreeningDate(), Sort.Direction.ASC, "screeningStartTime");
        assertThat(resultMap).isNotNull();
        assertThat(resultMap.size()).isEqualTo(1);
        assertThat(resultMap.containsKey(movie)).isTrue();
    }

    @DisplayName("JUnit test for saveScreening method")
    @Test
    public void givenScreeningObject_whenAddScreening_thenReturnScreeningObject() {
        given(screeningRepository.save(screening)).willReturn(screening);

        Screening savedScreening = screeningService.addScreening(screening);
        assertThat(savedScreening).isNotNull();
        assertThat(savedScreening).isInstanceOf(Screening.class);
    }

    @DisplayName("JUnit test for updateScreening method")
    @Test
    public void givenScreeningObject_whenUpdateScreening_thenReturnUpdateScreeningObject() {
        screening.setScreeningDate(LocalDate.MAX);

        given(screeningRepository.save(screening)).willReturn(screening);

        Screening updateScreening = screeningService.updateScreening(screening);
        assertThat(updateScreening).isNotNull();
        assertThat(updateScreening).isInstanceOf(Screening.class);
        assertThat(updateScreening.getScreeningDate()).isEqualTo(LocalDate.MAX);
    }

    @DisplayName("JUnit test for deleteScreening method")
    @Test
    public void givenScreeningObject_whenDeleteScreening_thenVoid() {
        willDoNothing().given(screeningRepository).delete(screening);
        screeningService.deleteScreening(screening);
        verify(screeningRepository, times(1)).delete(screening);
    }
}
