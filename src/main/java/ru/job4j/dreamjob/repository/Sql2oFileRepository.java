package ru.job4j.dreamjob.repository;


import org.springframework.stereotype.Repository;
import org.sql2o.Sql2o;
import ru.job4j.dreamjob.model.File;

import java.util.Optional;

/**
 * Класс-репозиторий для работы с файлами {@link File} в БД
 *
 * @author Artem Chernikov
 * @version 1.0
 * @since 26.01.2023
 */
@Repository
public class Sql2oFileRepository implements FileRepository {
    /**
     * Поле экземпляр {@link Sql2o} для работы с БД
     */
    private final Sql2o sql2o;

    public Sql2oFileRepository(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    /**
     * Метод используется для записи файла {@link File} в БД
     *
     * @param file - файл
     * @return - возвращает записанный файл
     */
    @Override
    public File save(File file) {
        try (var connection = sql2o.open()) {
            var query = connection.createQuery("INSERT INTO files (name, path) VALUES (:name, :path)", true)
                    .addParameter("name", file.getName())
                    .addParameter("path", file.getPath());
            int generatedId = query.executeUpdate().getKey(Integer.class);
            file.setId(generatedId);
            return file;
        }
    }

    /**
     * Метод используется для поиска файла {@link File} в БД по id
     *
     * @param id - id файла в БД
     * @return - возвращает {@link File} обернутый в {@link Optional}
     */
    @Override
    public Optional<File> findById(int id) {
        try (var connection = sql2o.open()) {
            var query = connection.createQuery("SELECT * FROM files WHERE id = :id");
            var file = query.addParameter("id", id).executeAndFetchFirst(File.class);
            return Optional.ofNullable(file);
        }
    }

    /**
     * Метод используется для удаления файла {@link File} по id в БД
     *
     * @param id - id файла
     * @return - возвращает true - если удаление успешно, false если нет
     */
    @Override
    public boolean deleteById(int id) {
        try (var connection = sql2o.open()) {
            var query = connection.createQuery("DELETE FROM files WHERE id = :id");
            var affectedRows = query.addParameter("id", id).executeUpdate().getResult();
            return affectedRows > 0;
        }
    }

}
