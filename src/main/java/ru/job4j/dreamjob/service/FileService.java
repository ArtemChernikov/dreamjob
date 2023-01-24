package ru.job4j.dreamjob.service;

import ru.job4j.dreamjob.dto.FileDto;
import ru.job4j.dreamjob.model.File;

import java.util.Optional;

/**
 * Общий интерфейс для сервисов по работе с файлами {@link File} и DTO {@link FileDto}
 *
 * @author Artem Chernikov
 * @version 1.0
 * @since 22.01.2023
 */

public interface FileService {
    File save(FileDto fileDto);

    Optional<FileDto> getFileById(int id);

    boolean deleteById(int id);
}
