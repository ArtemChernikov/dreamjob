package ru.job4j.dreamjob.service;

import ru.job4j.dreamjob.model.User;

import java.util.Optional;

/**
 * Общий интерфейс для сервисов по работе с пользователями {@link User}
 *
 * @author Artem Chernikov
 * @version 1.0
 * @since 30.01.2023
 */
public interface UserService {
    Optional<User> save(User user);

    Optional<User> findByEmailAndPassword(String email, String password);
}
