package ru.job4j.dreamjob.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.job4j.dreamjob.repository.CandidateRepository;
import ru.job4j.dreamjob.repository.MemoryVacancyRepository;
import ru.job4j.dreamjob.repository.VacancyRepository;

/**
 * Класс-контроллер для работы с вакансиями
 *
 * @author Artem Chernikov
 * @version 1.0
 * @since 14.01.2023
 */
@Controller
@RequestMapping("/vacancies")
public class VacancyController {
    /**
     * Поле {@link CandidateRepository} - хранилище с вакансиями
     */
    private final VacancyRepository vacancyRepository = MemoryVacancyRepository.getInstance();

    /**
     * Метод используется для вывода в браузере отображения всех вакансий
     *
     * @param model - {@link Model}
     * @return - возвращает отображение со всеми вакансиями
     */
    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("vacancies", vacancyRepository.findAll());
        return "vacancies/list";
    }

    /**
     * Метод используется для вывода в браузере отображения с созданием новой вакансии
     *
     * @return - возвращает отображение с созданием новой вакансии
     */
    @GetMapping("/create")
    public String getCreationPage() {
        return "vacancies/create";
    }
}
