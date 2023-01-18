package ru.job4j.dreamjob.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.job4j.dreamjob.repository.CandidateRepository;
import ru.job4j.dreamjob.repository.MemoryCandidateRepository;

/**
 * Класс-контроллер для работы с кандидатами
 *
 * @author Artem Chernikov
 * @version 1.0
 * @since 15.01.2023
 */
@Controller
@RequestMapping("/candidates")
public class CandidateController {
    /**
     * Поле {@link CandidateRepository} - хранилище с кандидатами
     */
    private final CandidateRepository candidateRepository = MemoryCandidateRepository.getInstance();

    /**
     * Метод используется для вывода в браузере отображения всех кандидатов
     *
     * @param model - {@link Model}
     * @return - возвращает отображение со всеми кандидатами
     */
    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("candidates", candidateRepository.findAll());
        return "candidates/list";
    }

    /**
     * Метод используется для вывода в браузере отображения с созданием нового кандидата
     *
     * @return - возвращает отображение с созданием нового кандидата
     */
    @GetMapping("/create")
    public String getCreationPage() {
        return "candidates/create";
    }
}
