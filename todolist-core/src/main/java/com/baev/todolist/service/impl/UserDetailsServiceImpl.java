package com.baev.todolist.service.impl;

import com.baev.todolist.domain.model.User;
import com.baev.todolist.domain.repository.UserRepository;
import com.baev.todolist.security.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;

/**
 * Spring security user details service implementation.
 *
 * @author Maxim Baev
 */
@Service("todoUserDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@PostConstruct
	private void init() {
		registerUsers();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("Unknown user name: " + username);
		}

		return new AppUser(user, new ArrayList<GrantedAuthority>());
	}

	/**
	 * Retrieves user by username.
	 *
	 * @param username username
	 * @return Found user.
	 */
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	/**
	 * Saves default users.
	 */
	private void registerUsers() {
		ensureUser("user", "user", "First", "Last");
		ensureUser("admin", "admin", "Admin", "Admin");
	}

	/**
	 * Ensure user exists.
	 *
	 * @param username username
	 * @param password password
	 */
	private void ensureUser(String username, String password, String firstName, String lastName) {
		User user = userRepository.findByUsername(username);
		if (user == null) {
			userRepository.save(new User(username, password));
		} else {
			user.setUsername(username);
			user.setPassword(password);
		}
	}
}
