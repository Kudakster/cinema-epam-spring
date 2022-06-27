package com.epam.cinema.spring.dto;

import com.epam.cinema.spring.enity.Screening;

import java.util.Arrays;

public class SeatReservedDto {

    private Integer[] seatId;

    private Screening screening;

    public Integer[] getSeatId() {
        return seatId;
    }

    public void setSeatId(Integer[] seatId) {
        this.seatId = seatId;
    }

    public Screening getScreening() {
        return screening;
    }

    public void setScreening(Screening screening) {
        this.screening = screening;
    }

    @Override
    public String toString() {
        return "SeatReservedDto{" +
                "seatId=" + Arrays.toString(seatId) +
                ", screening=" + screening +
                '}';
    }
}
