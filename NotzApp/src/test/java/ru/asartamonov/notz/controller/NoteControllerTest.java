package ru.asartamonov.notz.controller;

import domain.note.Note;
import org.junit.Before;
import org.junit.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.servlet.ModelAndView;
import repository.NotesRepository;
import ru.asartamonov.notz.service.NotesService;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class NoteControllerTest {

	NotesService notesService;
	NoteController noteController;
	NotesRepository notesRepository;
	Note note;
	List<Note> noteList;
	Page<Note> returnPage;
	HttpServletRequest request;
	HttpServletResponse response;

	@SuppressWarnings("unchecked")
	@Before
    public void setUp() throws Exception {
        notesRepository = mock(NotesRepository.class);
        notesService = new NotesService(notesRepository);
        noteController = new NoteController(notesService);
        note = new Note();
        noteList = new ArrayList<>();
        noteList.add(new Note());
        returnPage = (Page<Note>)mock(Page.class);
        request = mock(HttpServletRequestWrapper.class);
        response = mock(HttpServletResponseWrapper.class);
        
        when(notesRepository.saveAndFlush(note)).thenReturn(note);
        when(notesRepository.count()).thenReturn((long)noteList.size());
        when(returnPage.hasContent()).thenReturn(true);
        when(returnPage.getContent()).thenReturn(noteList);
        
        when(notesRepository.findAll(new PageRequest(anyInt(), 1))).thenReturn(returnPage);
    }

	@Test
	public void shouldReturnView() throws Exception {
		ModelAndView modelAndView = noteController.postNote(note, request, response);
		assertTrue(modelAndView.getViewName().equals("newnote"));
		assertTrue(modelAndView.getModel().containsKey("newnote"));
	}
}