package com.epam.cinema.spring.enity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
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

    @NotNull
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserFirstname() {
        return userFirstname;
    }

    public void setUserFirstname(String userFirstname) {
        this.userFirstname = userFirstname;
    }

    public String getUserSurname() {
        return userSurname;
    }

    public void setUserSurname(String userSurname) {
        this.userSurname = userSurname;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPhonenumber() {
        return userPhonenumber;
    }

    public void setUserPhonenumber(String userPhonenumber) {
        this.userPhonenumber = userPhonenumber;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

}