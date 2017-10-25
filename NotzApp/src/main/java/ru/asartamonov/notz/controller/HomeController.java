package ru.asartamonov.notz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import repository.NotesRepository;
import ru.asartamonov.notz.service.NotesService;

@Controller
public class HomeController {
	
	private NotesRepository notesRepository;
	private NotesService notesService;
	
	@Autowired
    public HomeController(NotesRepository notesRepository, NotesService notesService) {
		this.notesRepository = notesRepository;
		this.notesService = notesService;
	}

	@GetMapping("/")
    public ModelAndView gethome() {
        ModelMap model = new ModelMap();
        model.addAttribute("notesCount", notesRepository.count());
        model.addAttribute("randomnote", notesService.randomNote());
        return new ModelAndView("home", "model", model);
    }
}
