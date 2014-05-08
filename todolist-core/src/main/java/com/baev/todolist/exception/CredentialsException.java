package com.baev.todolist.exception;

/**
 * Credentials validation exception.
 *
 * @author Maxim Baev
 */
public class CredentialsException extends RuntimeException {

	private static final long serialVersionUID = 1256823078705583449L;

	/**
	 * Constructor.
	 *
	 * @param message message
	 */
	public CredentialsException(String message) {
		super(message);
	}
}
