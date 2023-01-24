package ru.job4j.dreamjob.model;

import java.time.LocalDateTime;
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
    private LocalDateTime creationDate = LocalDateTime.now();
    /**
     * Поле видимость вакансии в отображении
     */
    private boolean visible;
    /**
     * Поле id города
     */
    private int cityId;
    /**
     * Поле id файла
     */
    private int fileId;

    public Vacancy(int id, String title, String description, boolean visible, int cityId, int fileId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.visible = visible;
        this.cityId = cityId;
        this.fileId = fileId;
    }

    public Vacancy() {
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

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public boolean getVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public int getFileId() {
        return fileId;
    }

    public void setFileId(int fileId) {
        this.fileId = fileId;
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
