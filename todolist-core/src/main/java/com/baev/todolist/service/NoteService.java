package com.baev.todolist.service;

import com.baev.todolist.domain.model.Note;

import java.util.List;

/**
 * {@link Note} service.
 *
 * @author Maxim Baev
 */
public interface NoteService {

	/**
	 * Persists note.
	 *
	 * @param note note
	 * @return Saved note.
	 */
	Note create(Note note);

	/**
	 * Updates note.
	 *
	 * @param note note
	 * @return Updated note.
	 */
	Note update(Note note);

	/**
	 * Removes note.
	 *
	 * @param noteId note ID.
	 */
	void delete(Long noteId);

	/**
	 * Retrieves note by ID.
	 *
	 * @param noteId note ID
	 * @return Found note.
	 */
	Note findById(Long noteId);

	/**
	 * Get notes for current user using on paging parameters.
	 *
	 * @param userId  logged in user ID
	 * @param page    page number
	 * @param limit   page size
	 * @param sortCol sorting column name
	 * @param sortDir sorting direction
	 * @return List of notes.
	 */
	List<Note> findByUser(Long userId, int page, int limit, String sortCol, String sortDir);
}
