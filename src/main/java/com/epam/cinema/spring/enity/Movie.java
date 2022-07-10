package com.epam.cinema.spring.enity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Getter
@Setter
@Table(name = "movies")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_id", nullable = false)
    private Integer id;

    @NotNull(message = "error.notEmpty")
    @Column(name = "movie_name", nullable = false, length = 30)
    private String movieName;

    @Column(name = "movie_actors", length = 30)
    private String movieActors;

    @Column(name = "movie_direction", length = 30)
    private String movieDirection;

    @Column(name = "movie_genre", length = 30)
    private String movieGenre;

    @Column(name = "movie_country", length = 30)
    private String movieCountry;

    @Column(name = "movie_trailer_url", length = 50)
    private String movieTrailerUrl;

    @Column(name = "movie_img_url", length = 100)
    private String movieImgUrl;

    @NotNull(message = "error.notEmpty")
    @Column(name = "movie_release_date", nullable = false)
    private LocalDate movieReleaseDate;

    @NotNull(message = "error.notEmpty")
    @Column(name = "movie_duration_min", length = 4)
    private String movieDurationMin;

    @Column(name = "movie_description", length = 300)
    private String movieDescription;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Movie movie = (Movie) o;
        return id != null && Objects.equals(id, movie.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}