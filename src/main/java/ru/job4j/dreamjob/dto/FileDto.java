package ru.job4j.dreamjob.dto;

import ru.job4j.dreamjob.model.File;

/**
 * DTO (Data Transfer Object) для класса {@link File}
 *
 * @author Artem Chernikov
 * @version 1.0
 * @since 22.01.2023
 */
public class FileDto {
    /**
     * Поле имя файла
     */
    private String name;
    /**
     * Поле содержимое файла в байтах (контент)
     */
    private byte[] content;

    public FileDto(String name, byte[] content) {
        this.name = name;
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }
}
