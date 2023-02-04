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
import ru.job4j.dreamjob.model.Vacancy;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
     * @param postUser  - данные нового пользователя
     * @return - при успешной регистрации выполняется переход на страницу с вакансиями {@link Vacancy},
     * при вводе данных пользователя с уже существующим email перенаправляет на страницу ошибки
     */
    @PostMapping("/register")
    public String register(Model model, @ModelAttribute User postUser) {
        var newUser = userService.save(postUser);
        if (newUser.isEmpty()) {
            model.addAttribute("message", "Пользователь с данной почтой уже существует");
            return "errors/404";
        }
        return "redirect:/vacancies";
    }

    /**
     * Метод используется для отображения авторизации пользователя
     *
     * @return - возвращает отображение с входом в аккаунт
     */
    @GetMapping("/login")
    public String getLoginPage() {
        return "users/login";
    }

    /**
     * Метод используется для входа в аккаунт пользователя, если данные введены корректно
     * выполняется переход на страницу с вакансиями {@link Vacancy},
     * если нет то будет ошибка о неккоректности введенных данных
     *
     * @param postUser  - введенные данные пользователя для входа в аккаунт
     * @param model - {@link Model} используется для отображения ошибки о невалидных данных
     * @return - возвращает отображение с вакансиями, если данные введены корректно, если нет то возвращает страницу с авторизацией
     */
    @PostMapping("/login")
    public String loginUser(@ModelAttribute User postUser, Model model, HttpServletRequest request) {
        var userOptional = userService.findByEmailAndPassword(postUser.getEmail(), postUser.getPassword());
        if (userOptional.isEmpty()) {
            model.addAttribute("error", "Почта или пароль введены неверно");
            return "users/login";
        }
        var newSession = request.getSession();
        newSession.setAttribute("user", userOptional.get());
        return "redirect:/vacancies";
    }

    /**
     * Метод используется для выхода пользователя из системы
     *
     * @param session - {@link HttpSession} сессия пользователя
     * @return - возвращает отображение с авторизацией пользователя
     */
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/users/login";
    }
}
