package ru.job4j.dreamjob.service;

import ru.job4j.dreamjob.dto.FileDto;
import ru.job4j.dreamjob.model.Candidate;

import java.util.Collection;
import java.util.Optional;

/**
 * Интерфейс для реализаций сервисов для работы с кандидатами в хранилищах
 *
 * @author Artem Chernikov
 * @version 1.0
 * @since 19.01.2023
 */
public interface CandidateService {
    Candidate save(Candidate candidate, FileDto image);

    boolean deleteById(int id);

    boolean update(Candidate candidate, FileDto image);

    Optional<Candidate> findById(int id);

    Collection<Candidate> findAll();
}
