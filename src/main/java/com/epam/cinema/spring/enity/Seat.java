package com.epam.cinema.spring.enity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "seats")
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seat_id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "auditorium_id")
    private Auditorium auditorium;

    @Column(name = "seat_row", nullable = false)
    private Integer seatRow;

    @Column(name = "seat_number", nullable = false)
    private Integer seatNumber;

    public Seat(Auditorium auditorium, Integer seatRow, Integer seatNumber) {
        this.auditorium = auditorium;
        this.seatRow = seatRow;
        this.seatNumber = seatNumber;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Auditorium getAuditorium() {
        return auditorium;
    }

    public void setAuditorium(Auditorium auditorium) {
        this.auditorium = auditorium;
    }

    public Integer getSeatRow() {
        return seatRow;
    }

    public void setSeatRow(Integer seatRow) {
        this.seatRow = seatRow;
    }

    public Integer getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(Integer seatNumber) {
        this.seatNumber = seatNumber;
    }

    @Override
    public String toString() {
        return "Seat{" +
                "id=" + id +
                ", auditorium=" + auditorium +
                ", seatRow=" + seatRow +
                ", seatNumber=" + seatNumber +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Seat seat = (Seat) o;
        return id != null && Objects.equals(id, seat.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}