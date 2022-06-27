package com.epam.cinema.spring.service.implementation;

import com.epam.cinema.spring.enity.User;
import com.epam.cinema.spring.repository.UserRepository;
import com.epam.cinema.spring.service.IUserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findUserById(Integer id) {
        return userRepository.findById(Long.valueOf(id));
    }

    @Override
    public Optional<User> findUserByLogin(String login) {
        return userRepository.findByUserLogin(login);
    }

    @Override
    public boolean verifyUser(User user) {
        return userRepository.existsByUserLoginAndUserPassword(user.getUserLogin(), user.getUserPassword());
    }

    @Override
    public User addUser(User user) {
        if (userRepository.existsByUserLogin(user.getUserLogin())) {
            return null;
        }

        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(User user) {
        userRepository.delete(user);
    }
}
