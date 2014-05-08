package com.baev.todolist.service.impl;

import com.baev.todolist.domain.model.User;
import com.baev.todolist.domain.repository.UserRepository;
import com.baev.todolist.exception.UserException;
import com.baev.todolist.service.UserService;
import com.baev.todolist.util.ValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * {@link UserService} implementation.
 *
 * @author Maxim Baev
 */
@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public User create(User user) {
		ValidationUtils.validateUser(user);
		User existingUser = userRepository.findByUsername(user.getUsername());
		if (existingUser != null) {
			throw new UserException("User with such username already exists");
		}
		user.setId(null);
		user.setCreatedDate(new Date());
		return userRepository.save(user);
	}
}
