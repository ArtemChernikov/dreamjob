package ru.job4j.dreamjob.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Artem Chernikov
 * @version 1.0
 * @since 11.01.2023
 */
@RestController
public class IndexController {
    @GetMapping("/index")
    public String getIndex() {
        return "Hello World!";
    }
}
