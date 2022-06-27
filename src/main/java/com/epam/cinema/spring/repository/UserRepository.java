package com.epam.cinema.spring.repository;

import com.epam.cinema.spring.enity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserLogin(String userLogin);

    boolean existsByUserLoginAndUserPassword(String userLogin, String userPassword);

    boolean existsByUserLogin(String userLogin);


}
