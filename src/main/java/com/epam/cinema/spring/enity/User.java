package com.epam.cinema.spring.enity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Integer id;

    @NotNull
    @Size(min = 4, max = 30, message = "user.size.login")
    @Column(name = "user_login", nullable = false, length = 30)
    private String userLogin;

    @Column(name = "user_password", nullable = false, length = 30)
    private String userPassword;

    @NotNull
    @Size(min = 2, max = 30,
            message = "user.size.firstName")
    @Column(name = "user_firstname", nullable = false, length = 30)
    private String userFirstname;

    @NotNull
    @Size(min = 2, max = 30,
            message = "user.size.surName")
    @Column(name = "user_surname", nullable = false, length = 30)
    private String userSurname;

    @Pattern(regexp = "^\\S+@\\S+\\.\\S+$",
            message = "user.pattern.email")
    @Size(min = 5, max = 50, message = "user.size.email")
    @Column(name = "user_email", nullable = false, length = 50)
    private String userEmail;

    @Pattern(regexp = "\\+380[0-9]{9}", message = "user.pattern.phoneNumber")
    @Size(min = 13, max = 13,
            message = "user.size.phoneNumber")
    @Column(name = "user_phonenumber", nullable = false, length = 13)
    private String userPhonenumber;

    @Column(name = "user_role", nullable = false, length = 50)
    private String userRole;

    public User(Integer id, String userLogin) {
        this.id = id;
        this.userLogin = userLogin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        User user = (User) o;
        return id != null && Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}