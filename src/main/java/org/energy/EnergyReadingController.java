package org.energy;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Веб-контролер для перегляду показників електролічильників,
 * додавання нових записів та видалення існуючих.
 */
@Controller
public class EnergyReadingController {

    private final EnergyReadingRepository energyReadingRepository;

    public EnergyReadingController(
            EnergyReadingRepository energyReadingRepository) {
        this.energyReadingRepository = energyReadingRepository;
    }

    /**
     * Головна сторінка — відображає всі показники у вигляді таблиці.
     */
    @GetMapping("/")
    public String viewReadings(Model model) {
        List<EnergyReading> readings = energyReadingRepository.findAll();
        model.addAttribute("readings", readings);
        return "readings";
    }

    /**
     * Сторінка форми для додавання нового запису.
     */
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("reading", new EnergyReading());
        return "add";
    }

    /**
     * Зберігає новий запис до бази даних і перенаправляє на головну.
     */
    @PostMapping("/add")
    public String addReading(
            @ModelAttribute EnergyReading reading) {
        energyReadingRepository.save(reading);
        return "redirect:/";
    }

    /**
     * Сторінка форми для редагування існуючого запису.
     */
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable String id, Model model) {
        EnergyReading reading = energyReadingRepository.findById(id).orElseThrow();
        model.addAttribute("reading", reading);
        return "edit";
    }

    /**
     * Зберігає оновлений запис до бази даних і перенаправляє на головну.
     */
    @PostMapping("/edit/{id}")
    public String updateReading(
            @PathVariable String id,
            @ModelAttribute EnergyReading reading) {
        reading.setId(id);
        energyReadingRepository.save(reading);
        return "redirect:/";
    }

    /**
     * Видаляє запис за ідентифікатором і перенаправляє на головну.
     */
    @PostMapping("/delete/{id}")
    public String deleteReading(@PathVariable String id) {
        energyReadingRepository.deleteById(id);
        return "redirect:/";
    }
}