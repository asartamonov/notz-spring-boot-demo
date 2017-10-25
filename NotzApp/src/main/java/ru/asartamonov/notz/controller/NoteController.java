package ru.asartamonov.notz.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import domain.note.Note;
import ru.asartamonov.notz.service.NotesService;

@Controller
public class NoteController {

	private NotesService notesService;

	@Autowired
	public NoteController(NotesService notesService) {
		this.notesService = notesService;
	}

	@PostMapping("/newnote")
	public ModelAndView postNote(@ModelAttribute Note note, HttpServletRequest request, HttpServletResponse response) {
		//CsrfToken _csrf = (CsrfToken) request.getAttribute("_csrf");
		notesService.saveOneNote(note);
		Map<String, Object> model = new HashMap<>();
		model.put("newnote", note);
		model.put("randomnote", notesService.randomNote());
		return new ModelAndView("newnote", model);
	}

	@GetMapping("/newnote")
	public ModelAndView getNote(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> model = new HashMap<>();
		model.put("randomnote", getRandomNote());
		return new ModelAndView("newnote", model);
	}
	
	@GetMapping("/randomnote")
	public @ResponseBody Note getRandomNote() {
		return notesService.randomNote();
	}
}
