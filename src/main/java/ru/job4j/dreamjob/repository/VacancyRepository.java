package ru.job4j.dreamjob.repository;

import ru.job4j.dreamjob.model.Vacancy;

import java.util.Collection;
import java.util.Optional;

/**
 * Общий интерфейс всех репозиториев
 *
 * @author Artem Chernikov
 * @version 1.0
 * @since 14.01.2023
 */
public interface VacancyRepository {
    Vacancy save(Vacancy vacancy);

    boolean deleteById(int id);

    boolean update(Vacancy vacancy);

    Optional<Vacancy> findById(int id);

    Collection<Vacancy> findAll();
}
