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
@Table(name = "auditorium")
public class Auditorium {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "auditorium_id", nullable = false)
    private Integer id;

    @Column(name = "auditorium_name", nullable = false, length = 30)
    private String auditoriumName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAuditoriumName() {
        return auditoriumName;
    }

    public void setAuditoriumName(String auditoriumName) {
        this.auditoriumName = auditoriumName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Auditorium that = (Auditorium) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}