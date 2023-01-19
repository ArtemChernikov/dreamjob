package ru.job4j.dreamjob.service;

import ru.job4j.dreamjob.model.Vacancy;
import ru.job4j.dreamjob.repository.MemoryVacancyRepository;
import ru.job4j.dreamjob.repository.VacancyRepository;

import java.util.Collection;
import java.util.Optional;

/**
 * Класс-сервис для работы с вакансиями в хранилище
 *
 * @author Artem Chernikov
 * @version 1.0
 * @since 19.01.2023
 */
public class SimpleVacancyService implements VacancyService {
    private static final SimpleVacancyService INSTANCE = new SimpleVacancyService();
    /**
     * Поле хранилище вакансий
     */
    private final VacancyRepository vacancyRepository = MemoryVacancyRepository.getInstance();

    public static SimpleVacancyService getInstance() {
        return INSTANCE;
    }

    @Override
    public Vacancy save(Vacancy vacancy) {
        return vacancyRepository.save(vacancy);
    }

    @Override
    public boolean deleteById(int id) {
        return vacancyRepository.deleteById(id);
    }

    @Override
    public boolean update(Vacancy vacancy) {
        return vacancyRepository.update(vacancy);
    }

    @Override
    public Optional<Vacancy> findById(int id) {
        return vacancyRepository.findById(id);
    }

    @Override
    public Collection<Vacancy> findAll() {
        return vacancyRepository.findAll();
    }
}
