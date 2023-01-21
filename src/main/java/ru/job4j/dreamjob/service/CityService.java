package ru.job4j.dreamjob.service;

import ru.job4j.dreamjob.model.City;

import java.util.Collection;

/**
 * Интерфейс для реализаций сервисов для работы с городами в хранилищах
 *
 * @author Artem Chernikov
 * @version 1.0
 * @since 21.01.2023
 */
public interface CityService {
    Collection<City> findAll();
}
