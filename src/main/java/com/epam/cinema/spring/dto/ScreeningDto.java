package com.epam.cinema.spring.dto;

import com.epam.cinema.spring.enity.Screening;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Getter
@Setter
public class ScreeningDto {
    @NotEmpty(message = "error.notEmpty")
    private String movieName;
    @NotNull(message = "error.notEmpty")
    private LocalDate screeningDate;
    @NotNull(message = "error.notEmpty")
    private LocalTime screeningStartTime;
    @NotNull(message = "error.notEmpty")
    private LocalTime screeningEndTime;

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public Screening getScreeningFromDto() {
        Screening screening = new Screening();

        screening.setScreeningDate(screeningDate);
        screening.setScreeningStartTime(screeningStartTime);
        screening.setScreeningEndTime(screeningEndTime);

        return screening;
    }
}
