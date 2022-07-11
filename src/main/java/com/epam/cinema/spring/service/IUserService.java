package com.epam.cinema.spring.service;

import com.epam.cinema.spring.enity.User;

import java.util.List;
import java.util.Optional;


/**
 * The interface User service.
 */
public interface IUserService {

    /**
     * Find user by id optional.
     *
     * @param id the id
     * @return the optional
     */
    Optional<User> findUserById(Integer id);

    /**
     * Find user by login optional.
     *
     * @param login the login
     * @return the optional
     */
    Optional<User> findUserByLogin(String login);

    /**
     * Find all users list.
     *
     * @return the list
     */
    List<User> findAllUsers();

    /**
     * Add user user.
     *
     * @param user the user
     * @return the user
     */
    User addUser(User user);

    /**
     * Update user user.
     *
     * @param user the user
     * @return the user
     */
    User updateUser(User user);

    /**
     * Delete user.
     *
     * @param user the user
     */
    void deleteUser(User user);

    /**
     * Verify user.
     *
     * @param user the user
     * @return the boolean
     */
    boolean verifyUser(User user);

    boolean isUserExistWithSameLogin(String login);
}
