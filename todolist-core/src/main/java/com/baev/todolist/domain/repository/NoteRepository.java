package com.baev.todolist.domain.repository;

import com.baev.todolist.domain.model.Note;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Note repository.
 *
 * @author Maxim Baev
 */
@Repository("noteRepository")
public interface NoteRepository extends JpaRepository<Note, Long> {

	/**
	 * Get note page by user ID and paging params.
	 *
	 * @param userId   current user ID
	 * @param pageable paging params
	 * @return Page with notes.
	 */
	Page<Note> findByCreatedById(Long userId, Pageable pageable);
}
