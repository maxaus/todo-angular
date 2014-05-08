package com.baev.todolist.exception;

/**
 * User parameters exception.
 *
 * @author Maxim Baev
 */
public class UserException extends RuntimeException {
	private static final long serialVersionUID = -3092089690427494156L;

	/**
	 * {@inheritDoc}
	 */
	public UserException(String message) {
		super(message);
	}
}
