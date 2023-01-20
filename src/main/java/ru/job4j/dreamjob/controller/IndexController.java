package ru.job4j.dreamjob.controller;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Класс-контроллер начальной страницы нашего web-приложения
 *
 * @author Artem Chernikov
 * @version 1.0
 * @since 11.01.2023
 */
@ThreadSafe
@Controller
public class IndexController {
    @GetMapping("/index")
    public String getIndex() {
        return "index";
    }
}
