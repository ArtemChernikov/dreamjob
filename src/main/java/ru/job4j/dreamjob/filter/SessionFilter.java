package ru.job4j.dreamjob.filter;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import ru.job4j.dreamjob.model.User;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Класс-фильтр используется для корректного отображения страниц в веб-приложении,
 * демонстрирует выполнена авторизация или нет, всегда работает после {@link AuthorizationFilter}
 *
 * @author Artem Chernikov
 * @version 1.0
 * @since 04.02.2023
 */
@Component
@Order(2)
public class SessionFilter extends HttpFilter {
    /**
     * Метод используется для демонстрации авторизации пользователя (был выполнен вход или нет)
     * на странице в веб-приложении с помощью метода {@link SessionFilter#addUserToSession(HttpSession, HttpServletRequest)}
     *
     * @param request  - запрос
     * @param response - ответ
     * @param chain    - фильтр
     * @throws IOException      - {@link IOException}
     * @throws ServletException - {@link ServletException}
     */
    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response,
                            FilterChain chain) throws IOException, ServletException {
        var session = request.getSession();
        addUserToSession(session, request);
        chain.doFilter(request, response);
    }

    private void addUserToSession(HttpSession session, HttpServletRequest request) {
        var user = (User) session.getAttribute("user");
        if (user == null) {
            user = new User();
            user.setName("Гость");
        }
        request.setAttribute("user", user);
    }

}
