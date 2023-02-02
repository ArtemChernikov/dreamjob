package ru.job4j.dreamjob.repository;

import ru.job4j.dreamjob.model.User;

import java.util.Optional;

/**
 * Общий интерфейс всех репозиториев с пользователями
 *
 * @author Artem Chernikov
 * @version 1.0
 * @since 30.01.2023
 */
public interface UserRepository {
    Optional<User> save(User user);

    Optional<User> findByEmailAndPassword(String email, String password);
}
