package com.baev.todolist.util;


import com.baev.todolist.domain.model.Note;
import com.baev.todolist.domain.model.User;
import com.baev.todolist.exception.CredentialsException;
import com.baev.todolist.exception.NoteException;
import com.baev.todolist.exception.UserException;
import org.apache.commons.lang.StringUtils;

/**
 * Validation utility class.
 *
 * @author Maxim Baev
 */
public class ValidationUtils {

	/**
	 * Validates user.
	 *
	 * @param user user
	 */
	public static void validateUser(User user) {
		if (StringUtils.isBlank(user.getUsername()) || StringUtils.isBlank(user.getPassword())) {
			throw new UserException("Please check all required fields");
		}

		if (user.getPassword().length() < 3) {
			throw new CredentialsException("Password must have at least 3 characters");
		}
	}

	/**
	 * Validates To-Do list note.
	 *
	 * @param note note
	 */
	public static void validateNote(Note note) {
		if (StringUtils.isBlank(note.getTitle()) || StringUtils.isBlank(note.getDescription())) {
			throw new NoteException();
		}
	}
}
