package ru.job4j.dreamjob.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;
import ru.job4j.dreamjob.dto.FileDto;
import ru.job4j.dreamjob.model.File;
import ru.job4j.dreamjob.repository.FileRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.UUID;

/**
 * Класс-сервис для работы с файлами в репозитории и на накопителе
 *
 * @author Artem Chernikov
 * @version 1.0
 * @since 22.01.2023
 */
@Service
public class SimpleFileService implements FileService {
    /**
     * Поле репозиторий для хранения {@link File}
     */
    private final FileRepository fileRepository;
    /**
     * Поле путь хранения самих файлов
     */
    private final String storageDirectory;

    public SimpleFileService(FileRepository fileRepository,
                             @Value("${file.directory}") String storageDirectory) {
        this.fileRepository = fileRepository;
        this.storageDirectory = storageDirectory;
        createStorageDirectory(storageDirectory);
    }

    /**
     * Метод используется для создания директории для хранения файлов
     *
     * @param path - путь к директории
     */
    private void createStorageDirectory(String path) {
        try {
            Files.createDirectories(Path.of(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Метод используется для создания пути к файлу на основе
     * {@link SimpleFileService#storageDirectory} и названия файла
     *
     * @param sourceName - имя файла {@link FileDto}
     * @return - возвращает путь к файлу на диске
     */
    private String getNewFilePath(String sourceName) {
        return storageDirectory + java.io.File.separator + UUID.randomUUID() + sourceName;
    }

    /**
     * Метод используется для записи файла на накопитель по определенному пути
     *
     * @param path    - путь для записи
     * @param content - сам файл (контент)
     */
    private void writeFileBytes(String path, byte[] content) {
        try {
            Files.write(Path.of(path), content);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Метод используется для сохранения {@link File} в репозиторий и
     * записи самого файла по пути {@link SimpleFileService#storageDirectory}
     *
     * @param fileDto - {@link FileDto}
     * @return - возвращает доменное представления файла {@link File}
     */
    @Override
    public File save(FileDto fileDto) {
        var path = getNewFilePath(fileDto.getName());
        writeFileBytes(path, fileDto.getContent());
        return fileRepository.save(new File(fileDto.getName(), path));
    }

    /**
     * Метод используется для чтения контента по определенному пути
     *
     * @param path - путь к контенту
     * @return - возвращает массив с определенным контентом в байтовом представлении
     */
    private byte[] readFileAsBytes(String path) {
        try {
            return Files.readAllBytes(Path.of(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Метод используется для поиска {@link FileDto} по его id в репозитории
     *
     * @param id - id файла {@link File}
     * @return - возвращает {@link Optional<FileDto>}
     */
    @Override
    public Optional<FileDto> getFileById(int id) {
        var fileOptional = fileRepository.findById(id);
        if (fileOptional.isEmpty()) {
            return Optional.empty();
        }
        var content = readFileAsBytes(fileOptional.get().getPath());
        return Optional.of(new FileDto(fileOptional.get().getName(), content));
    }

    /**
     * Метод используется для удаления файла по пути
     *
     * @param path - путь к файлу для удаления
     */
    private void deleteFile(String path) {
        try {
            Files.deleteIfExists(Path.of(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Метод испольуется для удаления файла на накопителе по его id
     *
     * @param id - id файла
     * @return - возвращает true если удаление успешно и false если иначе
     */
    @Override
    public boolean deleteById(int id) {
        var fileOptional = fileRepository.findById(id);
        if (fileOptional.isEmpty()) {
            return false;
        }
        deleteFile(fileOptional.get().getPath());
        return fileRepository.deleteById(id);
    }


}
