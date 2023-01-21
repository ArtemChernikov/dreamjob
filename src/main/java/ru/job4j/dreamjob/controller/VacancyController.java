package ru.job4j.dreamjob.controller;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.dreamjob.model.Vacancy;
import ru.job4j.dreamjob.service.CityService;
import ru.job4j.dreamjob.service.VacancyService;

/**
 * Класс-контроллер для работы с вакансиями
 *
 * @author Artem Chernikov
 * @version 1.0
 * @since 14.01.2023
 */
@ThreadSafe
@Controller
@RequestMapping("/vacancies")
public class VacancyController {
    /**
     * Поле {@link VacancyService} - класс-сервис по работе с вакансиями
     */
    private final VacancyService vacancyService;
    /**
     * Поле {@link CityService} - класс-сервис по работе с городами
     */
    private final CityService cityService;

    public VacancyController(VacancyService vacancyService, CityService cityService) {
        this.vacancyService = vacancyService;
        this.cityService = cityService;
    }

    /**
     * Метод используется для вывода в браузере отображения всех вакансий
     *
     * @param model - {@link Model}
     * @return - возвращает отображение со всеми вакансиями
     */
    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("vacancies", vacancyService.findAll());
        return "vacancies/list";
    }

    /**
     * Метод используется для вывода в браузере отображения с созданием новой вакансии
     *
     * @return - возвращает отображение с созданием новой вакансии
     */
    @GetMapping("/create")
    public String getCreationPage(Model model) {
        model.addAttribute("cities", cityService.findAll());
        return "vacancies/create";
    }

    /**
     * Метод используется для сохранения новой вакансии в хранилище
     *
     * @param vacancy - вакансия
     * @return - возвращает отображение со списком всех вакансий
     */
    @PostMapping("/create")
    public String create(@ModelAttribute Vacancy vacancy) {
        vacancyService.save(vacancy);
        return "redirect:/vacancies";
    }

    /**
     * Метод используется для поиска по id и вывода отображения
     * с возможностью просмотра, редактирования и удаления вакансии
     *
     * @param model - {@link Model}
     * @param id    - id вакансии
     * @return - возвращает отображение с возможностью просмотра, редактирования и удаления вакансии
     */
    @GetMapping("/{id}")
    public String getById(Model model, @PathVariable int id) {
        var vacancyOptional = vacancyService.findById(id);
        if (vacancyOptional.isEmpty()) {
            model.addAttribute("message", "Вакансия с указанным идентификатором не найдена");
            return "errors/404";
        }
        model.addAttribute("cities", cityService.findAll());
        model.addAttribute("vacancy", vacancyOptional.get());
        return "vacancies/one";
    }

    /**
     * Метод используется для обновления данных о вакансии
     *
     * @param vacancy - вакансия
     * @param model   - {@link Model}
     * @return - возвращает отображение со списком всех вакансий
     */
    @PostMapping("/update")
    public String update(@ModelAttribute Vacancy vacancy, Model model) {
        var isUpdated = vacancyService.update(vacancy);
        if (!isUpdated) {
            model.addAttribute("message", "Вакансия с указанным идентификатором не найдена");
            return "errors/404";
        }
        return "redirect:/vacancies";
    }

    /**
     * Метод используется для удаления вакансии
     *
     * @param model - {@link Model}
     * @param id    - id вакансии
     * @return - возвращает отображение со списком всех вакансий
     */
    @GetMapping("/delete/{id}")
    public String delete(Model model, @PathVariable int id) {
        var isDeleted = vacancyService.deleteById(id);
        if (!isDeleted) {
            model.addAttribute("message", "Вакансия с указанным идентификатором не найдена");
            return "errors/404";
        }
        return "redirect:/vacancies";
    }
}
