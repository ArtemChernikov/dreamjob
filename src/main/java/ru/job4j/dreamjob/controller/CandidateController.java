package ru.job4j.dreamjob.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.dreamjob.model.Candidate;
import ru.job4j.dreamjob.model.Vacancy;
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

    /**
     * Метод используется для сохранения нового кандидата в хранилище
     *
     * @param candidate - кандидат
     * @return - возвращает отображение со списком всех кандидатов
     */
    @PostMapping("/create")
    public String create(@ModelAttribute Candidate candidate) {
        candidateRepository.save(candidate);
        return "redirect:/candidates";
    }

    /**
     * Метод используется для поиска по id и вывода отображения
     * с возможностью просмотра, редактирования и удаления кандидата
     *
     * @param model - {@link Model}
     * @param id    - id кандидата
     * @return - возвращает отображение с возможностью просмотра, редактирования и удаления кандидата
     */
    @GetMapping("/{id}")
    public String getById(Model model, @PathVariable int id) {
        var candidateOptional = candidateRepository.findById(id);
        if (candidateOptional.isEmpty()) {
            model.addAttribute("message", "Кандидат с указанным идентификатором не найден");
            return "errors/404";
        }
        model.addAttribute("candidate", candidateOptional.get());
        return "candidates/one";
    }

    /**
     * Метод используется для обновления данных о кандидате
     *
     * @param candidate - кандидат
     * @param model     - {@link Model}
     * @return - возвращает отображение со списком всех кандидатов
     */
    @PostMapping("/update")
    public String update(@ModelAttribute Candidate candidate, Model model) {
        var isUpdated = candidateRepository.update(candidate);
        if (!isUpdated) {
            model.addAttribute("message", "Кандидат с указанным идентификатором не найден");
            return "errors/404";
        }
        return "redirect:/candidates";
    }

    /**
     * Метод используется для удаления кандидата
     *
     * @param model - {@link Model}
     * @param id - id кандидата
     * @return - возвращает отображение со списком всех кандидатов
     */
    @GetMapping("/delete/{id}")
    public String delete(Model model, @PathVariable int id) {
        var isDeleted = candidateRepository.deleteById(id);
        if (!isDeleted) {
            model.addAttribute("message", "Кандидат с указанным идентификатором не найден");
            return "errors/404";
        }
        return "redirect:/candidates";
    }
}
