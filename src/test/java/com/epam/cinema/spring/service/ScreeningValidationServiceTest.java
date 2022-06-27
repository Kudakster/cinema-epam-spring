package com.epam.cinema.spring.service;

import com.epam.cinema.spring.enity.Movie;
import com.epam.cinema.spring.enity.Screening;
import com.epam.cinema.spring.service.implementation.ScreeningService;
import com.epam.cinema.spring.service.implementation.ScreeningValidationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class ScreeningValidationServiceTest {

    @Mock
    private ScreeningService screeningService;

    @InjectMocks
    private ScreeningValidationService screeningValidationService;

    private Screening screening;

    private Movie movie;

    @BeforeEach
    public void setup() {
        movie = Movie.builder()
                .movieDurationMin("30")
                .build();

        screening = Screening.builder()
                .id(1)
                .movie(movie)
                .screeningDate(LocalDate.now())
                .screeningStartTime(LocalTime.of(9,0))
                .screeningEndTime(LocalTime.of(9,30))
                .build();
    }

    @DisplayName("JUnit test for validateScreening method")
    @Test
    public void givenMovieObject_whenValidateScreening_thenReturnStringEmptyObject() {
        given(screeningService.findScreeningsByDate(screening.getScreeningDate())).willReturn(null);

        String result = screeningValidationService.validateScreening(screening);
        assertThat(result).isEmpty();
    }

    @DisplayName("JUnit test for validateScreeningDate method")
    @Test
    public void givenMovieObject_whenValidateScreeningDate_thenReturnStringObject() {
        screening.setScreeningDate(LocalDate.EPOCH);
        String result = screeningValidationService.validateScreening(screening);
        assertThat(result).isEqualTo("screening.date");
    }

    @DisplayName("JUnit test for validateScreeningTime method")
    @Test
    public void givenMovieObject_whenValidateScreeningStartTimeIsAfterNine_thenReturnStringObject() {
        screening.setScreeningStartTime(LocalTime.of(8,0));
        String result = screeningValidationService.validateScreening(screening);
        assertThat(result).isEqualTo("screening.startTimeIsAfterNine");
    }

    @DisplayName("JUnit test for validateScreeningTime method")
    @Test
    public void givenMovieObject_whenValidateScreeningStartTimeIsBeforeTwentyTwo_thenReturnStringObject() {
        screening.setScreeningStartTime(LocalTime.of(23,0));
        String result = screeningValidationService.validateScreening(screening);
        assertThat(result).isEqualTo("screening.startTimeIsBeforeTwentyTwo");
    }

    @DisplayName("JUnit test for validateScreeningTime method")
    @Test
    public void givenMovieObject_whenValidateScreeningStartTimeIsBeforeScreeningEndTime_thenReturnStringObject() {
        screening.setScreeningStartTime(LocalTime.of(10,0));
        screening.setScreeningEndTime(LocalTime.of(9,0));
        String result = screeningValidationService.validateScreening(screening);
        assertThat(result).isEqualTo("screening.time");
    }

    @DisplayName("JUnit test for validateDuration method")
    @Test
    public void givenMovieObject_whenValidateScreeningDuration_thenReturnStringObject() {
        screening.setScreeningStartTime(LocalTime.of(9,0));
        screening.setScreeningEndTime(LocalTime.of(9,20));
        String result = screeningValidationService.validateScreening(screening);
        assertThat(result).isEqualTo("screening.duration");
    }

    @DisplayName("JUnit test for validateDuration method")
    @Test
    public void givenMovieObject_whenValidateIsTimeAvailable_thenReturnStringObject() {
        Screening screeningAnother = Screening.builder()
                .id(2)
                .movie(movie)
                .screeningDate(LocalDate.now())
                .screeningStartTime(LocalTime.of(9,0))
                .screeningEndTime(LocalTime.of(10,0))
                .build();

        List<Screening> screenings = new ArrayList<>();
        screenings.add(screeningAnother);

        given(screeningService.findScreeningsByDate(screening.getScreeningDate())).willReturn(screenings);
        String result = screeningValidationService.validateScreening(screening);
        assertThat(result).isEqualTo("screening.timeIsTaking");
    }
}
