package com.epam.cinema.spring.repository;

import com.epam.cinema.spring.enity.Screening;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ScreeningRepository extends JpaRepository<Screening, Integer> {
    Optional<Screening> findByScreeningDateAndAuditorium_Id(LocalDate screeningDate, Integer id);

    List<Screening> findByScreeningDateGreaterThanEqualOrderByScreeningDateAscScreeningStartTimeAsc(LocalDate screeningDate);

    List<Screening> findByScreeningDateIsGreaterThanEqualAndScreeningStartTimeIsGreaterThanEqual(LocalDate screeningDate, LocalTime screeningStartTime, Sort by);

    boolean existsByMovie_Id(Integer id);


}