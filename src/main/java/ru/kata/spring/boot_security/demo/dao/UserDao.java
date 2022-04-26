package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {
    void addUser(User user);

    User getUserById(int id);

    User updateUser(User user);

    void removeUserById(int id);

    List<User> getAllUsers();

    Optional<User> findByEmail(String email);
}

