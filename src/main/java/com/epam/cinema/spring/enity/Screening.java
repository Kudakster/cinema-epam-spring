package com.epam.cinema.spring.enity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "screenings")
public class  Screening {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "screening_id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "auditorium_id")
    private Auditorium auditorium;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @Column(name = "screening_date")
    private LocalDate screeningDate;

    @Column(name = "screening_start_time")
    private LocalTime screeningStartTime;

    @Column(name = "screening_end_time")
    private LocalTime screeningEndTime;

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

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
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

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "screeningDate = " + screeningDate + ", " +
                "screeningStartTime = " + screeningStartTime + ", " +
                "screeningEndTime = " + screeningEndTime + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Screening screening = (Screening) o;
        return id != null && Objects.equals(id, screening.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}