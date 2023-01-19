package ru.job4j.dreamjob.service;

import ru.job4j.dreamjob.model.Vacancy;

import java.util.Collection;
import java.util.Optional;

/**
 * Интерфейс для реализаций сервисов для работы с вакансиями в хранилищах
 *
 * @author Artem Chernikov
 * @version 1.0
 * @since 19.01.2023
 */
public interface VacancyService {
    Vacancy save(Vacancy vacancy);

    boolean deleteById(int id);

    boolean update(Vacancy vacancy);

    Optional<Vacancy> findById(int id);

    Collection<Vacancy> findAll();
}
