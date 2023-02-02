package ru.job4j.dreamjob.service;

import ru.job4j.dreamjob.dto.FileDto;
import ru.job4j.dreamjob.model.Vacancy;

import java.util.Collection;
import java.util.Optional;

/**
 * Общий интерфейс для сервисов по работе с вакансиями {@link Vacancy}
 *
 * @author Artem Chernikov
 * @version 1.0
 * @since 19.01.2023
 */
public interface VacancyService {
    Vacancy save(Vacancy vacancy, FileDto image);

    boolean deleteById(int id);

    boolean update(Vacancy vacancy, FileDto image);

    Optional<Vacancy> findById(int id);

    Collection<Vacancy> findAll();
}
