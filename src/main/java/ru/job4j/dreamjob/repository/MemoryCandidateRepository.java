package ru.job4j.dreamjob.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.dreamjob.model.Candidate;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Класс описывает хранилище кандидатов в оперативной памяти сервера
 *
 * @author Artem Chernikov
 * @version 1.0
 * @since 15.01.2023
 */
@Repository
public class MemoryCandidateRepository implements CandidateRepository {

    private static final MemoryCandidateRepository INSTANCE = new MemoryCandidateRepository();

    private int nextId = 1;

    private final Map<Integer, Candidate> candidates = new HashMap<>();

    public MemoryCandidateRepository() {
        save(new Candidate(0, "Иванов Виталий Викторович", "Стажер"));
        save(new Candidate(0, "Черасский Леонид Андреевич", "Джуниор"));
        save(new Candidate(0, "Петров Сергей Витальевич", "Джуниор+"));
        save(new Candidate(0, "Орлов Игорь Олегович", "Мидл"));
        save(new Candidate(0, "Мишустин Федор Александрович", "Мидл+"));
        save(new Candidate(0, "Андреев Андрей Андреевич", "Сеньор"));
    }

    public static MemoryCandidateRepository getInstance() {
        return INSTANCE;
    }

    @Override
    public Candidate save(Candidate candidate) {
        candidate.setId(nextId++);
        candidates.put(candidate.getId(), candidate);
        return candidate;
    }

    @Override
    public boolean deleteById(int id) {
        return candidates.remove(id) != null;
    }

    @Override
    public boolean update(Candidate candidate) {
        return candidates.computeIfPresent(candidate.getId(), (id, oldCandidate) ->
                new Candidate(oldCandidate.getId(), candidate.getName(),
                        candidate.getDescription())) != null;
    }

    @Override
    public Optional<Candidate> findById(int id) {
        return Optional.ofNullable(candidates.get(id));
    }

    @Override
    public Collection<Candidate> findAll() {
        return candidates.values();
    }
}
