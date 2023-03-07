package ru.job4j.dreamjob.repository;

import lombok.extern.slf4j.Slf4j;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Repository;
import org.sql2o.Sql2o;
import ru.job4j.dreamjob.model.User;

import java.util.Optional;

/**
 * Класс-репозиторий для работы с пользователями {@link User} в БД
 *
 * @author Artem Chernikov
 * @version 1.0
 * @since 30.01.2023
 */
@ThreadSafe
@Slf4j
@Repository
public class Sql2oUserRepository implements UserRepository {
    /**
     * Поле экземпляр {@link Sql2o} для работы с БД
     */
    private final Sql2o sql2o;

    public Sql2oUserRepository(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    /**
     * Метод используется для записи нового пользователя {@link User} в БД
     * Исключает добавление пользователя с уже существующим в БД email
     *
     * @param user - новый пользователь
     * @return - возвращает {@link User} обернутый в {@link Optional}
     */
    @Override
    public Optional<User> save(User user) {
        try (var connection = sql2o.open()) {
                var sql = """
                        INSERT INTO users (email, name, password) VALUES (:email, :name, :password)
                        """;
                var query = connection.createQuery(sql, true)
                        .addParameter("email", user.getEmail())
                        .addParameter("name", user.getName())
                        .addParameter("password", user.getPassword());
                int generatedId = query.executeUpdate().getKey(Integer.class);
                user.setId(generatedId);
                return Optional.of(user);
            } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return Optional.empty();
    }

    /**
     * Метод используется для поиска пользователя {@link User} в БД по email и password
     *
     * @param email    - email пользователя
     * @param password - ппроль пользователя
     * @return - возвращает {@link User} обернутый в {@link Optional}
     */
    @Override
    public Optional<User> findByEmailAndPassword(String email, String password) {
        try (var connection = sql2o.open()) {
            var query = connection.createQuery("""
                    SELECT * FROM users WHERE email = :email AND password = :password
                    """);
            query.addParameter("email", email);
            query.addParameter("password", password);
            return Optional.ofNullable(query.executeAndFetchFirst(User.class));
        }
    }
}
