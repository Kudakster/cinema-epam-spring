package com.epam.cinema.spring.service;

import com.epam.cinema.spring.enity.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {

    Optional<User> findUserById(Integer id);

    Optional<User> findUserByLogin(String login);

    List<User> findAllUsers();

    User addUser(User user);

    User updateUser(User user);

    void deleteUser(User user);

    boolean verifyUser(User user);
}
