package ru.job4j.dreamjob.service;

import ru.job4j.dreamjob.model.Candidate;
import ru.job4j.dreamjob.repository.CandidateRepository;
import ru.job4j.dreamjob.repository.MemoryCandidateRepository;

import java.util.Collection;
import java.util.Optional;

/**
 * Класс-сервис для работы с кандидатами в хранилище
 *
 * @author Artem Chernikov
 * @version 1.0
 * @since 19.01.2023
 */
public class SimpleCandidateService implements CandidateService {
    private static final SimpleCandidateService INSTANCE = new SimpleCandidateService();

    /**
     * Поле хранилище кандидатов
     */
    private final CandidateRepository candidateRepository = MemoryCandidateRepository.getInstance();

    public static SimpleCandidateService getInstance() {
        return INSTANCE;
    }

    @Override
    public Candidate save(Candidate candidate) {
        return candidateRepository.save(candidate);
    }

    @Override
    public boolean deleteById(int id) {
        return candidateRepository.deleteById(id);
    }

    @Override
    public boolean update(Candidate candidate) {
        return candidateRepository.update(candidate);
    }

    @Override
    public Optional<Candidate> findById(int id) {
        return candidateRepository.findById(id);
    }

    @Override
    public Collection<Candidate> findAll() {
        return candidateRepository.findAll();
    }
}
