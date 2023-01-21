package ru.job4j.dreamjob.model;

/**
 * Класс описывает модель города
 *
 * @author Artem Chernikov
 * @version 1.0
 * @since 21.01.2023
 */
public class City {
    private final int id;

    private final String name;

    public City(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
