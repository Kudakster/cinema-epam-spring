package com.epam.cinema.spring.enity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getMovieActors() {
        return movieActors;
    }

    public void setMovieActors(String movieActors) {
        this.movieActors = movieActors;
    }

    public String getMovieDirection() {
        return movieDirection;
    }

    public void setMovieDirection(String movieDirection) {
        this.movieDirection = movieDirection;
    }

    public String getMovieGenre() {
        return movieGenre;
    }

    public void setMovieGenre(String movieGenre) {
        this.movieGenre = movieGenre;
    }

    public String getMovieCountry() {
        return movieCountry;
    }

    public void setMovieCountry(String movieCountry) {
        this.movieCountry = movieCountry;
    }

    public String getMovieTrailerUrl() {
        return movieTrailerUrl;
    }

    public void setMovieTrailerUrl(String movieTrailerUrl) {
        this.movieTrailerUrl = movieTrailerUrl;
    }

    public String getMovieImgUrl() {
        return movieImgUrl;
    }

    public void setMovieImgUrl(String movieImgUrl) {
        this.movieImgUrl = movieImgUrl;
    }

    public LocalDate getMovieReleaseDate() {
        return movieReleaseDate;
    }

    public void setMovieReleaseDate(LocalDate movieReleaseDate) {
        this.movieReleaseDate = movieReleaseDate;
    }

    public String getMovieDurationMin() {
        return movieDurationMin;
    }

    public void setMovieDurationMin(String movieDurationMin) {
        this.movieDurationMin = movieDurationMin;
    }

    public String getMovieDescription() {
        return movieDescription;
    }

    public void setMovieDescription(String movieDescription) {
        this.movieDescription = movieDescription;
    }

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