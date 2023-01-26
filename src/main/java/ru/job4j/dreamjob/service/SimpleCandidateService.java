package ru.job4j.dreamjob.service;

import org.springframework.stereotype.Service;
import ru.job4j.dreamjob.dto.FileDto;
import ru.job4j.dreamjob.model.Candidate;
import ru.job4j.dreamjob.repository.CandidateRepository;

import java.util.Collection;
import java.util.Optional;

/**
 * Класс-сервис для работы с кандидатами {@link Candidate} в репозитории
 *
 * @author Artem Chernikov
 * @version 1.0
 * @since 19.01.2023
 */
@Service
public class SimpleCandidateService implements CandidateService {
    /**
     * Поле хранилище кандидатов
     */
    private final CandidateRepository candidateRepository;

    /**
     * Поле класс-сервис для работы с файлами
     */
    private final FileService fileService;

    public SimpleCandidateService(CandidateRepository sql2oCandidateRepository, FileService fileService) {
        this.candidateRepository = sql2oCandidateRepository;
        this.fileService = fileService;
    }

    /**
     * Метод испольузется для записи файла с помощью {@link FileService}
     * и присвоения id файла кандидата {@link Candidate}
     *
     * @param candidate - кандидат
     * @param image     - файл
     */
    private void saveNewFile(Candidate candidate, FileDto image) {
        var file = fileService.save(image);
        candidate.setFileId(file.getId());
    }

    @Override
    public Candidate save(Candidate candidate, FileDto image) {
        saveNewFile(candidate, image);
        return candidateRepository.save(candidate);
    }

    @Override
    public boolean deleteById(int id) {
        return candidateRepository.deleteById(id);
    }

    @Override
    public boolean update(Candidate candidate, FileDto image) {
        var isNewFileEmpty = image.getContent().length == 0;
        if (isNewFileEmpty) {
            return candidateRepository.update(candidate);
        }
        /* если передан новый не пустой файл, то старый удаляем, а новый сохраняем */
        var oldFileId = candidate.getFileId();
        saveNewFile(candidate, image);
        var isUpdated = candidateRepository.update(candidate);
        fileService.deleteById(oldFileId);
        return isUpdated;
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
