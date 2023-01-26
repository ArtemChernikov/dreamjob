package ru.job4j.dreamjob.repository;

import org.springframework.stereotype.Repository;
import org.sql2o.Sql2o;
import ru.job4j.dreamjob.model.Vacancy;

import java.util.Collection;
import java.util.Optional;

/**
 * Класс-репозиторий для работы с вакансиями {@link Vacancy} в БД
 *
 * @author Artem Chernikov
 * @version 1.0
 * @since 26.01.2023
 */
@Repository
public class Sql2oVacancyRepository implements VacancyRepository {
    /**
     * Поле экземпляр {@link Sql2o} для работы с БД
     */
    private final Sql2o sql2o;

    public Sql2oVacancyRepository(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    /**
     * Метод используется для записи вакансии {@link Vacancy} в БД
     *
     * @param vacancy - вакансия
     * @return - возвращает записанную вакансию
     */
    @Override
    public Vacancy save(Vacancy vacancy) {
        try (var connection = sql2o.open()) {
            var sql = """
                    INSERT INTO vacancies(title, description, creation_date, visible, city_id, file_id)
                    VALUES (:title, :description, :creationDate, :visible, :cityId, :fileId)
                    """;
            var query = connection.createQuery(sql, true)
                    .addParameter("title", vacancy.getTitle())
                    .addParameter("description", vacancy.getDescription())
                    .addParameter("creationDate", vacancy.getCreationDate())
                    .addParameter("visible", vacancy.getVisible())
                    .addParameter("cityId", vacancy.getCityId())
                    .addParameter("fileId", vacancy.getFileId());
            int generatedId = query.executeUpdate().getKey(Integer.class);
            vacancy.setId(generatedId);
            return vacancy;
        }
    }

    /**
     * Метод используется для удаления вакансии {@link Vacancy} по id в БД
     *
     * @param id - id вакансии
     * @return - возвращает true - если удаление успешно, false если нет
     */
    @Override
    public boolean deleteById(int id) {
        try (var connection = sql2o.open()) {
            var query = connection.createQuery("DELETE FROM vacancies WHERE id = :id");
            query.addParameter("id", id);
            var affectedRows = query.executeUpdate().getResult();
            return affectedRows > 0;
        }
    }

    /**
     * Метод используется для обновления вакансии {@link Vacancy} в БД
     *
     * @param vacancy - новые данные {@link Vacancy}
     * @return - возвращает true - если обновление успешно, false если нет
     */
    @Override
    public boolean update(Vacancy vacancy) {
        try (var connection = sql2o.open()) {
            var sql = """
                    UPDATE vacancies
                    SET title = :title, description = :description, creation_date = :creationDate,
                        visible = :visible, city_id = :cityId, file_id = :fileId
                    WHERE id = :id
                    """;
            var query = connection.createQuery(sql)
                    .addParameter("title", vacancy.getTitle())
                    .addParameter("description", vacancy.getDescription())
                    .addParameter("creationDate", vacancy.getCreationDate())
                    .addParameter("visible", vacancy.getVisible())
                    .addParameter("cityId", vacancy.getCityId())
                    .addParameter("fileId", vacancy.getFileId())
                    .addParameter("id", vacancy.getId());
            var affectedRows = query.executeUpdate().getResult();
            return affectedRows > 0;
        }
    }

    /**
     * Метод используется для поиска вакансии {@link Vacancy} в БД по id
     *
     * @param id - id вакансии
     * @return - возвращает {@link Vacancy} обернутый в {@link Optional}
     */
    @Override
    public Optional<Vacancy> findById(int id) {
        try (var connection = sql2o.open()) {
            var query = connection.createQuery("SELECT * FROM vacancies WHERE id = :id");
            query.addParameter("id", id);
            var vacancy = query.setColumnMappings(Vacancy.COLUMN_MAPPING).executeAndFetchFirst(Vacancy.class);
            return Optional.ofNullable(vacancy);
        }
    }

    /**
     * Метод используется для поиска всех вакансий {@link Vacancy} в БД
     *
     * @return - возвращает коллекцию вакансий
     */
    @Override
    public Collection<Vacancy> findAll() {
        try (var connection = sql2o.open()) {
            var query = connection.createQuery("SELECT * FROM vacancies");
            return query.setColumnMappings(Vacancy.COLUMN_MAPPING).executeAndFetch(Vacancy.class);
        }
    }

}
