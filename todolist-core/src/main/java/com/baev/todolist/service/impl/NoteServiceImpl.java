package com.baev.todolist.service.impl;

import com.baev.todolist.domain.model.Note;
import com.baev.todolist.domain.repository.NoteRepository;
import com.baev.todolist.service.NoteService;
import com.baev.todolist.util.ValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * {@link NoteService} implementation.
 *
 * @author Maxim Baev
 */
@Service("noteService")
public class NoteServiceImpl implements NoteService {

	@Autowired
	private NoteRepository noteRepository;

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public Note create(final Note note) {

		note.setId(null);
		note.setCompleted(false);
		note.setCreatedDate(new Date());
		note.setUpdatedDate(new Date());

		ValidationUtils.validateNote(note);

		return noteRepository.save(note);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public Note update(final Note note) {
		note.setUpdatedDate(new Date());

		ValidationUtils.validateNote(note);
		return noteRepository.save(note);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public void delete(final Long noteId) {
		noteRepository.delete(noteId);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional(readOnly = true)
	public Note findById(Long noteId) {
		return noteRepository.findOne(noteId);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Note> findByUser(Long userId, int page, int limit, String sortCol, String sortDir) {
		Sort sort = null;
		if (sortCol != null) {
			if (sortDir == null) {
				sortDir = Sort.Direction.ASC.name();
			}
			sort = new Sort(Sort.Direction.fromString(sortDir), sortCol);
		}

		Pageable pageable = new PageRequest(page, limit, sort);
		return noteRepository.findByCreatedById(userId, pageable).getContent();
	}
}
