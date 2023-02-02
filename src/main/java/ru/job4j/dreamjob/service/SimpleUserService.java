package ru.job4j.dreamjob.service;

import org.springframework.stereotype.Service;
import ru.job4j.dreamjob.model.User;
import ru.job4j.dreamjob.repository.UserRepository;

import java.util.Optional;

/**
 * Класс-сервис для работы с пользователями {@link User} в репозитории
 *
 * @author Artem Chernikov
 * @version 1.0
 * @since 30.01.2023
 */
@Service
public class SimpleUserService implements UserService {
    /**
     * Поле хранилище пользователей
     */
    private final UserRepository userRepository;

    public SimpleUserService(UserRepository sql2oUserRepository) {
        this.userRepository = sql2oUserRepository;
    }

    @Override
    public Optional<User> save(User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findByEmailAndPassword(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password);
    }
}
