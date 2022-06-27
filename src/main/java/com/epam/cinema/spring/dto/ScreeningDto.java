package com.epam.cinema.spring.dto;

import com.epam.cinema.spring.enity.Screening;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class ScreeningDto {
    @NotEmpty(message = "error.notEmpty")
    private String movieName;
    @NotNull(message = "error.notEmpty")
    private LocalDate screeningDate;
    @NotNull(message = "error.notEmpty")
    private LocalTime screeningStartTime;
    @NotNull(message = "error.notEmpty")
    private LocalTime screeningEndTime;

    public String getMovie() {
        return movieName;
    }

    public void setMovie(String movieName) {
        this.movieName = movieName;
    }

    public LocalDate getScreeningDate() {
        return screeningDate;
    }

    public void setScreeningDate(LocalDate screeningDate) {
        this.screeningDate = screeningDate;
    }

    public LocalTime getScreeningStartTime() {
        return screeningStartTime;
    }

    public void setScreeningStartTime(LocalTime screeningStartTime) {
        this.screeningStartTime = screeningStartTime;
    }

    public LocalTime getScreeningEndTime() {
        return screeningEndTime;
    }

    public void setScreeningEndTime(LocalTime screeningEndTime) {
        this.screeningEndTime = screeningEndTime;
    }

    public Screening getScreeningFromDto() {
        Screening screening = new Screening();

        screening.setScreeningDate(screeningDate);
        screening.setScreeningStartTime(screeningStartTime);
        screening.setScreeningEndTime(screeningEndTime);

        return screening;
    }
}
