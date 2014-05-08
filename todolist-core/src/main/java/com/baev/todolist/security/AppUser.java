package com.baev.todolist.security;

import com.baev.todolist.domain.model.User;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.Collection;

/**
 * Application user DTO.
 *
 * @author Maxim Baev
 */
public class AppUser extends org.springframework.security.core.userdetails.User implements Serializable {

	private static final long serialVersionUID = 3637520184475587882L;

	private User user;

	/**
	 * Constructor.
	 *
	 * @param user user
	 * @param authorities authorities
	 */
	public AppUser(User user, Collection<? extends GrantedAuthority> authorities) {
		super(user.getUsername(), user.getPassword(), true, true, true, true, authorities);
		this.user = user;
	}

	/**
	 * Gets user.
	 *
	 * @return the user
	 */
	public User getUser() {
		return user;
	}
}
