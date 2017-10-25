package ru.asartamonov.notz.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import domain.note.Note;
import repository.NotesRepository;

@Service
public class NotesService {
	
	private NotesRepository notesRepository;

	@Autowired
	public NotesService(NotesRepository notesRepository) {
		this.notesRepository = notesRepository;
	}
	
	public Note saveOneNote(Note note) {
		Note newNote = note;
		newNote.setDateTime(LocalDateTime.now());
		notesRepository.saveAndFlush(newNote);
		return newNote;
	}
	
	public Note randomNote() {
		Long qty = notesRepository.count();
		int idx = (int) (Math.random() * qty);
		Page<Note> notePage = notesRepository.findAll(new PageRequest(idx, 1));
		Note note = null;
		if (notePage.hasContent()) {
			note = notePage.getContent().get(0);
		}
		return note;
	}
	
}
