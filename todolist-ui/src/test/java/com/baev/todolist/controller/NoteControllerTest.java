package com.baev.todolist.controller;

import com.baev.todolist.controller.rest.NoteController;
import com.baev.todolist.controller.rest.dto.NoteDto;
import com.baev.todolist.domain.model.Note;
import com.baev.todolist.domain.model.User;
import com.baev.todolist.service.AuthenticationService;
import com.baev.todolist.service.NoteService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * Tests {@link NoteController} instance.
 *
 * @author Maxim Baev
 */
public class NoteControllerTest {

	private static final Long TEST_USER_ID = 2L;
	private static final Long TEST_NOTE_ID = 1L;
	private static final String DATE_FORMAT = "yyyy-MM-dd";

	@InjectMocks
	private NoteController noteController;

	@Mock
	private NoteService noteService;

	@Mock
	private AuthenticationService authenticationService;

	@Before
	public void setup() {
		initMocks(this);
	}

	@Test
	public void testGetNote() {
		Note note = new Note();
		when(noteService.findById(TEST_NOTE_ID)).thenReturn(note);

		Note result = noteController.getNote(TEST_NOTE_ID);
		verify(noteService).findById(TEST_NOTE_ID);
		assertNotNull(result);
		assertEquals(note, result);
	}

	@Test
	public void testGetNotesForCurrentUser() {
		List<Note> noteList = Collections.singletonList(new Note());
		User user = new User();
		user.setId(TEST_USER_ID);
		when(authenticationService.getCurrentUser()).thenReturn(user);
		when(noteService.findByUser(eq(TEST_USER_ID), anyInt(), anyInt(), anyString(), anyString())).thenReturn(noteList);
		Map<String, Object> res = noteController.getNotesForCurrentUser("DUE_DATE", "DESC");
		List<Note> result = (List<Note>) res.get("list");
		verify(authenticationService).getCurrentUser();
		assertNotNull(result);
		assertEquals(1, result.size());
	}

	@Test
	public void testSaveNote() throws ParseException {
		Note note = new Note();
		when(noteService.create(eq(note))).thenReturn(note);

		NoteDto noteDto = new NoteDto();
		noteDto.setDueDate(new SimpleDateFormat(DATE_FORMAT).format(new Date()));
		Map<String, Object> result = noteController.saveNote(noteDto);
		assertNotNull(result);
		assertEquals(1, result.size());
	}

	@Test
	public void testUpdateNote() throws ParseException {
		Note note = new Note();
		when(noteService.findById(TEST_NOTE_ID)).thenReturn(note);
		when(noteService.update(note)).thenReturn(note);

		NoteDto noteDto = new NoteDto();
		noteDto.setDueDate(new SimpleDateFormat(DATE_FORMAT).format(new Date()));
		noteDto.setDescription(note.getDescription());
		noteDto.setTitle(note.getTitle());

		noteController.updateNote(noteDto, TEST_NOTE_ID);
		verify(noteService).findById(TEST_NOTE_ID);
		verify(noteService).update(note);
	}

	@Test
	public void testDeleteNote() {
		doNothing().when(noteService).delete(TEST_NOTE_ID);
		noteController.deleteNot(TEST_NOTE_ID);
		verify(noteService).delete(TEST_NOTE_ID);
	}
}
