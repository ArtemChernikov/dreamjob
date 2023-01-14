package ru.job4j.dreamjob.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * Класс описывает модель вакансии
 *
 * @author Artem Chernikov
 * @version 1.0
 * @since 14.01.2023
 */
public class Vacancy {
    /**
     * Поле id вакансии
     */
    private int id;
    /**
     * Поле название вакансии
     */
    private String title;
    /**
     * Поле описание вакансии
     */
    private String description;
    /**
     * Поле дата и время создания вакансии
     */
    private String creationDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss"));

    public Vacancy(int id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Vacancy vacancy = (Vacancy) o;
        return id == vacancy.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
