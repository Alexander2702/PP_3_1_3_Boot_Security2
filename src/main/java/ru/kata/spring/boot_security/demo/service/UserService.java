package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    void addUser(User user);

    User getUserById(int id);

    void updateUser(User user);

    void removeUserById(int id);

    List<User> getAllUsers();

    Optional<User> findByEmail(String email);
}

