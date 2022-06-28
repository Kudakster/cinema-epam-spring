package com.epam.cinema.spring.service;

import com.epam.cinema.spring.enity.Screening;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public interface IScreeningService {
    Optional<Screening> findScreeningById(Integer id);

    Optional<Screening>  findScreeningByDateAndAuditoriumID(LocalDate date, Integer id);

    List<Screening> findAllScreenings();

    List<Screening> findScreeningsByDate(LocalDate date);

    List<Screening> findScreeningsByDateAndTime(LocalDate date, LocalTime time, Sort.Direction direction, String sortBy);

    Screening addScreening(Screening screening);

    Screening updateScreening(Screening screening);

    boolean isScreeningExistByMovieId(Integer id);

    boolean isScreeningHaveTickets(Integer id);

    void deleteScreening(Screening screening);
}
