package com.baev.todolist.domain.repository;

import com.baev.todolist.BasePersistenceTest;
import com.baev.todolist.domain.model.Note;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Date;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.TestCase.assertNotNull;

/**
 * {@link NoteRepository} test.
 *
 * @author Maxim Baev
 */
public class NoteRepositoryTest extends BasePersistenceTest {

	private static final Long TEST_USER_ID = 1L;

	@Autowired
	private NoteRepository noteRepository;

	@Autowired
	private UserRepository userRepository;

	@Test
	public void testLifecycle() {

		Page<Note> page = noteRepository.findByCreatedById(TEST_USER_ID, new PageRequest(0, 10));
		assertNotNull(page);

		List<Note> notes = page.getContent();
		assertEquals(3, notes.size());

		Note note = new Note();
		note.setTitle("New note");
		note.setPriority(1);
		note.setCreatedDate(new Date());
		note.setUpdatedDate(new Date());
		note.setCreatedBy(userRepository.findOne(TEST_USER_ID));

		note = noteRepository.save(note);
		Assert.assertNotNull(note.getId());
		page = noteRepository.findByCreatedById(TEST_USER_ID, new PageRequest(0, 10));
		notes = page.getContent();
		assertEquals(4, notes.size());

		noteRepository.delete(note);
		page = noteRepository.findByCreatedById(TEST_USER_ID, new PageRequest(0, 10));
		notes = page.getContent();
		assertEquals(3, notes.size());
	}
}
