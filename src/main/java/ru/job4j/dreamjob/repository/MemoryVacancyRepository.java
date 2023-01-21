package ru.job4j.dreamjob.repository;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Repository;
import ru.job4j.dreamjob.model.Vacancy;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Класс описывает хранилище вакансий в оперативной памяти сервера
 *
 * @author Artem Chernikov
 * @version 1.0
 * @since 14.01.2023
 */
@ThreadSafe
@Repository
public class MemoryVacancyRepository implements VacancyRepository {

    private final AtomicInteger nextId = new AtomicInteger(0);

    private final Map<Integer, Vacancy> vacancies = new HashMap<>();

    public MemoryVacancyRepository() {
        save(new Vacancy(0, "Intern Java Developer", "Стажер", true, 1));
        save(new Vacancy(0, "Junior Java Developer", "Джуниор", true, 1));
        save(new Vacancy(0, "Junior+ Java Developer", "Джуниор+", true, 2));
        save(new Vacancy(0, "Middle Java Developer", "Мидл", true, 3));
        save(new Vacancy(0, "Middle+ Java Developer", "Мидл+", true, 3));
        save(new Vacancy(0, "Senior Java Developer", "Сеньор", true, 2));
    }

    @Override
    public Vacancy save(Vacancy vacancy) {
        vacancy.setId(nextId.incrementAndGet());
        vacancies.put(vacancy.getId(), vacancy);
        return vacancy;
    }

    @Override
    public boolean deleteById(int id) {
        return vacancies.remove(id) != null;
    }

    @Override
    public boolean update(Vacancy vacancy) {
        return vacancies.computeIfPresent(vacancy.getId(), (id, oldVacancy) ->
                new Vacancy(oldVacancy.getId(), vacancy.getTitle(),
                        vacancy.getDescription(), vacancy.getVisible(), vacancy.getCityId())) != null;
    }

    @Override
    public Optional<Vacancy> findById(int id) {
        return Optional.ofNullable(vacancies.get(id));
    }

    @Override
    public Collection<Vacancy> findAll() {
        return vacancies.values();
    }
}
