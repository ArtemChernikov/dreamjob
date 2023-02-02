package ru.job4j.dreamjob.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.sql2o.Sql2o;
import ru.job4j.dreamjob.configuration.DatasourceConfiguration;
import ru.job4j.dreamjob.model.User;

import java.util.Properties;

import static org.assertj.core.api.Assertions.*;

class Sql2oUserRepositoryTest {

    private static Sql2oUserRepository sql2oUserRepository;

    private static Sql2o sql2o;

    @BeforeAll
    public static void initRepositories() throws Exception {
        var properties = new Properties();
        try (var inputStream = Sql2oCandidateRepository.class.getClassLoader().getResourceAsStream("connection.properties")) {
            properties.load(inputStream);
        }
        var url = properties.getProperty("datasource.url");
        var username = properties.getProperty("datasource.username");
        var password = properties.getProperty("datasource.password");

        var configuration = new DatasourceConfiguration();
        var connection = configuration.connectionPool(url, username, password);
        sql2o = configuration.databaseClient(connection);

        sql2oUserRepository = new Sql2oUserRepository(sql2o);
    }

    @AfterEach
    public void clearUsers() {
        try (var connection = sql2o.open()) {
            var query = connection.createQuery("DELETE FROM users");
            query.executeUpdate();
        }
    }

    @Test
    public void whenSaveThenGetSame() {
        var savedUser = sql2oUserRepository.save(new User(0, "qwerty@yandex.ru",
                "Artem", "12345"));
        assertThat(savedUser).usingRecursiveComparison().isEqualTo(sql2oUserRepository.findByEmailAndPassword("qwerty@yandex.ru", "12345"));
    }

    @Test
    public void whenSaveSameUsers() {
        var user = new User(0, "qwerty@yandex.ru",
                "Artem", "12345");
        sql2oUserRepository.save(user);
        var duplicateUser = sql2oUserRepository.save(user);
        assertThat(duplicateUser).isEmpty();
    }

    @Test
    public void whenSaveThenGetAnotherEmail() {
        var user = new User(0, "qwerty@yandex.ru",
                "Artem", "12345");
        sql2oUserRepository.save(user);
        assertThat(sql2oUserRepository.findByEmailAndPassword("qwerty@google.com", "12345")).isEmpty();
    }

    @Test
    public void whenSaveThenGetAnotherPassword() {
        var user = new User(0, "qwerty@yandex.ru",
                "Artem", "12345");
        sql2oUserRepository.save(user);
        assertThat(sql2oUserRepository.findByEmailAndPassword("qwerty@yandex.ru", "123456")).isEmpty();
    }
}