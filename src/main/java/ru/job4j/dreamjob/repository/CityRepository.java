package ru.job4j.dreamjob.repository;

import ru.job4j.dreamjob.model.City;

import java.util.Collection;

/**
 * Общий интерфейс всех репозиториев c городами
 *
 * @author Artem Chernikov
 * @version 1.0
 * @since 21.01.2023
 */
public interface CityRepository {
    Collection<City> findAll();
}
