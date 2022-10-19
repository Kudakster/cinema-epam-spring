package com.epam.cinema.spring.dto;

import com.epam.cinema.spring.enity.Screening;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;

@Getter
@Setter
public class SeatReservedDto {
    private Integer[] seatId;
    private Screening screening;

    @Override
    public String toString() {
        return "SeatReservedDto{" +
                "seatId=" + Arrays.toString(seatId) +
                ", screening=" + screening +
                '}';
    }
}
