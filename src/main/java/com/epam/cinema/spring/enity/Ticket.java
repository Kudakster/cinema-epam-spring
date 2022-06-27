package com.epam.cinema.spring.enity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "tickets")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticket_id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seat_reserved_id")
    private SeatReserved seatReserved;

    public Ticket(User user, SeatReserved seatReserved) {
        this.user = user;
        this.seatReserved = seatReserved;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public SeatReserved getSeatReserved() {
        return seatReserved;
    }

    public void setSeatReserved(SeatReserved seatReserved) {
        this.seatReserved = seatReserved;
    }

}