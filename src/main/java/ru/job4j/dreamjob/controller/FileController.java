package ru.job4j.dreamjob.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.job4j.dreamjob.service.FileService;
import ru.job4j.dreamjob.model.File;

/**
 * Класс-контроллер для работы с файлами {@link File}
 *
 * @author Artem Chernikov
 * @version 1.0
 * @since 22.01.2023
 */
@RestController
@RequestMapping("/files")
public class FileController {
    /**
     * Поле сервис для работы с файлами
     */
    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    /**
     * Метод используется для взаимодействия с файлом по id,
     * если файл не найден по id, то клиенту возвращается статус 404,
     * а если найден, то статус 200 с телом ответа в виде содержимого файла
     *
     * @param id - id файла
     * @return - возвращает файл в отображении
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable int id) {
        var contentOptional = fileService.getFileById(id);
        if (contentOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(contentOptional.get().getContent());
    }
}
