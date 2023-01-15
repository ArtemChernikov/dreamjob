package ru.job4j.dreamjob.repository;

import ru.job4j.dreamjob.model.Candidate;

import java.util.Collection;
import java.util.Optional;

/**
 * Общий интерфейс всех репозиториев c кандидатами
 *
 * @author Artem Chernikov
 * @version 1.0
 * @since 15.01.2023
 */
public interface CandidateRepository {
    Candidate save(Candidate candidate);

    boolean deleteById(int id);

    boolean update(Candidate candidate);

    Optional<Candidate> findById(int id);

    Collection<Candidate> findAll();
}
