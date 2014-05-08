package com.baev.todolist.service.impl;

import com.baev.todolist.domain.model.User;
import com.baev.todolist.security.AppUser;
import com.baev.todolist.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * {@link com.baev.todolist.service.AuthenticationService} implementation.
 *
 * @author Maxim Baev
 */
@Service("authInfoService")
public class AuthenticationServiceImpl implements AuthenticationService {

	@Autowired
	private UserDetailsServiceImpl userService;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public User getCurrentUser() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal != null && principal instanceof AppUser) {
			return ((AppUser) principal).getUser();
		}
		return null;
	}
}
