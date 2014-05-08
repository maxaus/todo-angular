package com.baev.todolist.service;

import com.baev.todolist.domain.model.User;

/**
 * Authentication service.
 *
 * @author Maxim Baev
 */
public interface AuthenticationService {

	/**
	 * Get current user.
	 *
	 * @return Current user
	 */
	User getCurrentUser();
}
