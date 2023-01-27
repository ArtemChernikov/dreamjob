package ru.job4j.dreamjob.model;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;

/**
 * Класс описывает модель кандидата на вакансию
 *
 * @author Artem Chernikov
 * @version 1.0
 * @since 15.01.2023
 */
public class Candidate {
    /**
     * Метод используется для маппинга модели кандидата {@link Candidate}
     * где ключи это названия столбцов в БД, а значения это названия полей модели
     */
    public static final Map<String, String> COLUMN_MAPPING = Map.of(
            "id", "id",
            "name", "name",
            "description", "description",
            "creation_date", "creationDate",
            "city_id", "cityId",
            "file_id", "fileId"
    );
    /**
     * Поле id кандидата
     */
    private int id;
    /**
     * Поле название желаемой должности кандидата
     */
    private String name;
    /**
     * Поле описание кандидата
     */
    private String description;
    /**
     * Поле дата и время появления кандидата
     */
    private LocalDateTime creationDate = LocalDateTime.now();
    /**
     * Поле id города кандидата
     */
    private int cityId;

    /**
     * Поле id файла канидата
     */
    private int fileID;

    public Candidate(int id, String name, String description, int cityId, int fileID) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.cityId = cityId;
        this.fileID = fileID;
    }

    public Candidate(int id, String name, String description, LocalDateTime creationDate, int cityId, int fileID) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.creationDate = creationDate;
        this.cityId = cityId;
        this.fileID = fileID;
    }

    public Candidate() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public int getFileId() {
        return fileID;
    }

    public void setFileId(int fileID) {
        this.fileID = fileID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Candidate candidate = (Candidate) o;
        return id == candidate.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
