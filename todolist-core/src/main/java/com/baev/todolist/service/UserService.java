package com.baev.todolist.service;

import com.baev.todolist.domain.model.User;

/**
 * {@link User} service.
 *
 * @author Maxim Baev
 */
public interface UserService {

	/**
	 * Creates new user in DB.
	 *
	 * @param user user
	 * @return new user
	 */
	User create(User user);
}
