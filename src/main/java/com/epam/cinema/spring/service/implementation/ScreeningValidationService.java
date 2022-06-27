package com.epam.cinema.spring.service.implementation;

import com.epam.cinema.spring.enity.Screening;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoField;
import java.util.List;

@Service
public class ScreeningValidationService {
    @Autowired
    private ScreeningService screeningService;

    public String validateScreening(Screening screening) {
        if (!validateScreeningDate(screening.getScreeningDate())) {
            return "screening.date";
        } else if (!validateScreeningStartTimeIsAfterNineOrEquals(screening.getScreeningStartTime())) {
            return "screening.startTimeIsAfterNine";
        } else if (!validateScreeningStartTimeIsBeforeTwentyTwoOrEquals(screening.getScreeningStartTime())) {
            return "screening.startTimeIsBeforeTwentyTwo";
        }else if (!validateScreeningStartTimeIsBeforeScreeningEndTime(screening.getScreeningStartTime(), screening.getScreeningEndTime())) {
            return "screening.time";
        } else if (!validateDuration(screening.getScreeningStartTime(), screening.getScreeningEndTime(), Long.parseLong(screening.getMovie().getMovieDurationMin()))) {
            return "screening.duration";
        } else if (!validateIsTimeAvailable(screening)) {
            return "screening.timeIsTaking";
        } else {
            return "";
        }
    }

    private boolean validateScreeningDate(LocalDate date) {
        return date.isEqual(LocalDate.now()) || date.isAfter(LocalDate.now());
    }

    private boolean validateScreeningStartTimeIsAfterNineOrEquals(LocalTime time) {
        return time.equals(LocalTime.of(9, 0)) || time.isAfter(LocalTime.of(9, 0));
    }

    private boolean validateScreeningStartTimeIsBeforeTwentyTwoOrEquals(LocalTime time) {
        return time.equals(LocalTime.of(22, 0)) || time.isBefore(LocalTime.of(22, 0));
    }

    private boolean validateScreeningStartTimeIsBeforeScreeningEndTime(LocalTime startTime, LocalTime endTime) {
        return startTime.isBefore(endTime);
    }

    private boolean validateDuration(LocalTime startTime, LocalTime endTime, long durationMovie) {
        long durationScreening = endTime.getLong(ChronoField.MINUTE_OF_DAY) - startTime.getLong(ChronoField.MINUTE_OF_DAY);
        return durationMovie <= durationScreening;
    }

    private boolean validateIsTimeAvailable(Screening screening) {
        List<Screening> screenings = screeningService.findScreeningsByDate(screening.getScreeningDate());

        if (screenings != null) {
            if (screening.getId() != null) {
                screenings.removeIf(sc -> sc.getId().equals(screening.getId()));
            }
            return screenings.stream().allMatch(sc -> isTimeAvailable(screening, sc.getScreeningStartTime(), sc.getScreeningEndTime()));
        }
         return true;
    }

    private boolean isTimeAvailable(Screening screening, LocalTime startTime, LocalTime endTime) {
        if ((endTime.isAfter(LocalTime.MIDNIGHT) || endTime.equals(LocalTime.MIDNIGHT))
                && endTime.isBefore(LocalTime.of(9, 0))) {
            endTime = LocalTime.MAX;
        }

        return (screening.getScreeningStartTime().isBefore(startTime) &&
                screening.getScreeningEndTime().isBefore(startTime)) ||
                (screening.getScreeningStartTime().isAfter(endTime) &&
                        screening.getScreeningEndTime().isAfter(endTime));
    }

}
