package ru.job4j.dreamjob.repository;

import org.springframework.stereotype.Repository;
import org.sql2o.Sql2o;
import ru.job4j.dreamjob.model.City;

import java.util.Collection;

/**
 * Класс-репозиторий для работы с городами {@link City} в БД
 *
 * @author Artem Chernikov
 * @version 1.0
 * @since 26.01.2023
 */
@Repository
public class Sql2oCityRepository implements CityRepository {
    /**
     * Поле экземпляр {@link Sql2o} для работы с БД
     */
    private final Sql2o sql2o;

    public Sql2oCityRepository(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    /**
     * Метод используется для поиска всех городов в БД
     *
     * @return - возвращает коллекцию из городов {@link City}
     */
    @Override
    public Collection<City> findAll() {
        try (var connection = sql2o.open()) {
            var query = connection.createQuery("SELECT * FROM cities");
            return query.executeAndFetch(City.class);
        }
    }

}
