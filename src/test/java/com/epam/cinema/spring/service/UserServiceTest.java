package com.epam.cinema.spring.service;

import com.epam.cinema.spring.enity.User;
import com.epam.cinema.spring.repository.UserRepository;
import com.epam.cinema.spring.service.implementation.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private User user;

    @BeforeEach
    public void setup() {
        user = User.builder()
                .id(1)
                .userLogin("user")
                .userPassword("password")
                .userFirstname("Sergiy")
                .userSurname("Kondratiuk")
                .userRole("USER")
                .build();
    }

    @DisplayName("JUnit test for saveUser method")
    @Test
    public void givenUserObject_whenAddUser_thenReturnUserObject() {
        given(userRepository.save(user)).willReturn(user);

        User savedUser = userService.addUser(user);
        assertThat(savedUser).isNotNull();
        assertThat(savedUser).isInstanceOf(User.class);
    }

    @DisplayName("JUnit test for saveUser method")
    @Test
    public void givenUserObject_whenAddUser_thenReturnNull() {
        User userWithSameLogin = User.builder()
                .id(2)
                .userLogin("user")
                .userPassword("password")
                .userFirstname("Sergiy")
                .userSurname("Kondratiuk")
                .userRole("USER")
                .build();

        given(userRepository.existsByUserLogin(userWithSameLogin.getUserLogin()))
                .willReturn(true);

        User savedUser = userService.addUser(userWithSameLogin);
        assertThat(savedUser).isNull();
    }

    @DisplayName("JUnit test for findUser method")
    @Test
    public void givenUserObject_whenFindUserByID_thenReturnUserObject() {
        given(userRepository.findById(Long.valueOf(user.getId())))
                .willReturn(Optional.of(user));

        Optional<User> findUser = userService.findUserById(user.getId());
        assertThat(findUser).isPresent();
        assertThat(findUser.get()).isInstanceOf(User.class);
    }

    @DisplayName("JUnit test for findUser method")
    @Test
    public void givenUserObject_whenFindUserByLogin_thenReturnUserObject() {
        given(userRepository.findByUserLogin(user.getUserLogin()))
                .willReturn(Optional.of(user));

        Optional<User> findUser = userService.findUserByLogin(user.getUserLogin());
        assertThat(findUser).isPresent();
        assertThat(findUser.get()).isInstanceOf(User.class);
    }

    @DisplayName("JUnit test for findAllUser method")
    @Test
    public void givenUserObject_whenFindAllUser_thenReturnUserObject() {
        User anotherUser = User.builder()
                .id(2)
                .userLogin("anotherUser")
                .userFirstname("Kolia")
                .userSurname("Semenhuk")
                .userRole("USER")
                .build();

        given(userRepository.findAll()).willReturn(List.of(user, anotherUser));

        List<User> users = userService.findAllUsers();
        assertThat(users).isNotNull();
        assertThat(users.size()).isEqualTo(2);
    }

    @DisplayName("JUnit test for verifyUser method")
    @Test
    public void givenUserObject_whenVerifyUser_thenReturnBoolean() {
        given(userRepository.existsByUserLoginAndUserPassword(user.getUserLogin(), user.getUserPassword()))
                .willReturn(true);

        boolean result = userService.verifyUser(user);
        assertThat(result).isTrue();
    }

    @DisplayName("JUnit test for updateUser method")
    @Test
    public void givenUserObject_whenUpdateUser_thenReturnUpdateUserObject() {
        user.setUserLogin("updateUser");

        given(userRepository.save(user)).willReturn(user);

        User updateUser = userService.addUser(user);
        assertThat(updateUser).isNotNull();
        assertThat(updateUser).isInstanceOf(User.class);
        assertThat(updateUser.getUserLogin()).isEqualTo("updateUser");
    }

    @DisplayName("JUnit test for deleteUser method")
    @Test
    public void givenUserObject_whenDeleteUser_thenVoid() {
        willDoNothing().given(userRepository).delete(user);
        userService.deleteUser(user);
        verify(userRepository, times(1)).delete(user);
    }
}
