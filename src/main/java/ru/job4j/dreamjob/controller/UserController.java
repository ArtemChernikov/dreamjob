package ru.job4j.dreamjob.controller;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.job4j.dreamjob.model.User;
import ru.job4j.dreamjob.service.UserService;

/**
 * Класс-контроллер для работы с пользователями {@link User}
 *
 * @author Artem Chernikov
 * @version 1.0
 * @since 30.01.2023
 */
@ThreadSafe
@Controller
@RequestMapping("/users")
public class UserController {
    /**
     * Поле класс-сервис {@link UserService} по работе с пользователями {@link User}
     */
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Метод используется для отображения страницы с регистрацией нового пользователя {@link User}
     *
     * @return - возвращает страницу с регистрацией
     */
    @GetMapping("/register")
    public String getRegistrationPage() {
        return "users/registration";
    }

    /**
     * Метод используется для регистрации нового пользователя {@link User} и сохранения его в БД
     *
     * @param model - {@link Model}
     * @param user  - данные нового пользователя
     * @return - при успешной регистрации возвращает на главную страницу,
     * при вводе данных пользователя с уже существующим email перенаправляет на страницу ошибки
     */
    @PostMapping("/register")
    public String register(Model model, @ModelAttribute User user) {
        var newUser = userService.save(user);
        if (newUser.isEmpty()) {
            model.addAttribute("message", "Пользователь с данной почтой уже существует");
            return "errors/404";
        }
        return "redirect:/index";
    }
}
