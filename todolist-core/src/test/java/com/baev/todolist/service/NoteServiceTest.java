package com.baev.todolist.service;

import com.baev.todolist.BaseMockitoTest;
import com.baev.todolist.domain.model.Note;
import com.baev.todolist.domain.repository.NoteRepository;
import com.baev.todolist.service.impl.NoteServiceImpl;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

/**
 * {@link NoteService} test.
 *
 * @author Maxim Baev
 */
public class NoteServiceTest extends BaseMockitoTest {

	private static final Long TEST_NOTE_ID = 1L;
	private static final String TEST_NOTE_TITLE = "Note";
	private static final String TEST_NOTE_DESCRIPTION = "Desc";

	@InjectMocks
	private NoteServiceImpl noteService;

	@Mock
	private NoteRepository noteRepository;

	@Test
	public void testSave() {
		final Note note = new Note();
		note.setTitle(TEST_NOTE_TITLE);
		note.setDescription(TEST_NOTE_DESCRIPTION);
		when(noteRepository.save(note)).thenReturn(note);

		final Note result = noteService.create(note);
		verify(noteRepository).save(note);
		assertNotNull(result);
		assertEquals(note, result);
	}

	@Test
	public void testUpdate() {
		final Note note = new Note();
		note.setTitle(TEST_NOTE_TITLE);
		note.setDescription(TEST_NOTE_DESCRIPTION);
		when(noteRepository.save(note)).thenReturn(note);

		final Note result = noteService.update(note);
		verify(noteRepository).save(note);
		assertNotNull(result);
		assertEquals(note, result);
	}

	@Test
	public void testDelete() {
		doNothing().when(noteRepository).delete(TEST_NOTE_ID);
		noteService.delete(TEST_NOTE_ID);
		verify(noteRepository).delete(TEST_NOTE_ID);
	}

	@Test
	public void testFindById() {
		final Note note = new Note();
		when(noteRepository.findOne(TEST_NOTE_ID)).thenReturn(note);
		final Note result = noteService.findById(TEST_NOTE_ID);
		verify(noteRepository).findOne(TEST_NOTE_ID);
		assertNotNull(result);
		assertEquals(note, result);
	}

	@Test
	public void testFindByCreatedById() {
		Long userId = 1L;
		int page = 1;
		int limit = 10;
		String sortCol = "title";
		String sortDir = Sort.Direction.ASC.name();

		Pageable pageable = new PageRequest(page, limit, new Sort(Sort.Direction.fromString(sortDir), sortCol));

		List<Note> noteList = Collections.singletonList(new Note());
		PageImpl<Note> notes = new PageImpl<>(noteList, null, 0);
		when(noteRepository.findByCreatedById(eq(userId), any(Pageable.class))).thenReturn(notes);
		List<Note> result = noteService.findByUser(userId, page, limit, sortCol, sortDir);
		verify(noteRepository).findByCreatedById(userId, pageable);
		assertNotNull(result);
		assertEquals(1, result.size());
	}
}
