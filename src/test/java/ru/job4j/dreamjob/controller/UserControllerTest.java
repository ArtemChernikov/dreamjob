package ru.job4j.dreamjob.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.ui.ConcurrentModel;
import ru.job4j.dreamjob.model.User;
import ru.job4j.dreamjob.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

class UserControllerTest {
    private UserService userService;
    private UserController userController;

    @BeforeEach
    public void initServices() {
        userService = mock(UserService.class);
        userController = new UserController(userService);
    }

    @Test
    public void whenRequestRegisterThenGetRegistrationPage() {
        var view = userController.getRegistrationPage();
        assertThat(view).isEqualTo("users/registration");
    }

    @Test
    public void whenPostRegisterNewUserThenGetVacanciesPage() {
        var user = new User(1, "email@yandex.ru", "username", "12345");
        var userArgumentCaptor = ArgumentCaptor.forClass(User.class);
        when(userService.save(userArgumentCaptor.capture())).thenReturn(Optional.of(user));

        var model = new ConcurrentModel();
        var view = userController.register(model, user);
        var actualUser = userArgumentCaptor.getValue();

        assertThat(view).isEqualTo("redirect:/vacancies");
        assertThat(actualUser).usingRecursiveComparison().isEqualTo(user);
    }

    @Test
    public void whenPostRegisterNewUserThenGetErrorPage() {
        var user = new User(1, "email@yandex.ru", "username", "12345");
        var userArgumentCaptor = ArgumentCaptor.forClass(User.class);
        var expectedMessage = "Пользователь с данной почтой уже существует";
        when(userService.save(userArgumentCaptor.capture())).thenReturn(Optional.empty());

        var model = new ConcurrentModel();
        var view = userController.register(model, user);
        var actualMessage = model.getAttribute("message");
        var actualUser = userArgumentCaptor.getValue();

        assertThat(view).isEqualTo("errors/404");
        assertThat(actualMessage).isEqualTo(expectedMessage);
        assertThat(actualUser).usingRecursiveComparison().isEqualTo(user);
    }

    @Test
    public void whenRequestLoginPageThenGetLoginPage() {
        var view = userController.getLoginPage();
        assertThat(view).isEqualTo("users/login");
    }

    @Test
    public void whenPostLoginUserThenGetVacanciesPage() {
        var httpSession = mock(HttpSession.class);
        var httpServletRequest = mock(HttpServletRequest.class);
        var user = new User(1, "email@yandex.ru", "username", "12345");
        when(userService.findByEmailAndPassword(any(String.class), any(String.class))).thenReturn(Optional.of(user));
        when(httpServletRequest.getSession()).thenReturn(httpSession);
        when(httpSession.getAttribute("user")).thenReturn(user);

        var model = new ConcurrentModel();
        var view = userController.loginUser(user, model, httpServletRequest);
        var actualUser = (User) httpServletRequest.getSession().getAttribute("user");

        assertThat(view).isEqualTo("redirect:/vacancies");
        assertThat(actualUser).usingRecursiveComparison().isEqualTo(user);
    }

    @Test
    public void whenPostLoginUserThenErrorAndGetLoginPage() {
        var httpServletRequest = mock(HttpServletRequest.class);
        var user = new User(1, "email@yandex.ru", "username", "12345");
        var expectedMessage = "Почта или пароль введены неверно";
        when(userService.findByEmailAndPassword(any(String.class), any(String.class))).thenReturn(Optional.empty());

        var model = new ConcurrentModel();
        var view = userController.loginUser(user, model, httpServletRequest);
        var actualMessage = model.getAttribute("error");

        assertThat(view).isEqualTo("users/login");
        assertThat(actualMessage).isEqualTo(expectedMessage);
    }

    @Test
    public void whenLogOutTheGetLoginPage() {
        var httpSession = mock(HttpSession.class);
        var view = userController.logout(httpSession);
        assertThat(view).isEqualTo("redirect:/users/login");
    }

}