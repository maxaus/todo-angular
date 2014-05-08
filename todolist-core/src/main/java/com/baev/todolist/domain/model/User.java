package com.baev.todolist.domain.model;

import org.apache.commons.lang.builder.ToStringBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Application user entity.
 *
 * @author Maxim Baev
 */
@Entity
@Table(name = "user")
public class User extends AbstractEntity {

	private static final long serialVersionUID = 6735051286030145204L;

	/**
	 * Username
	 */
	@Column(name = "username", nullable = false, unique = true)
	private String username;

	/**
	 * Password
	 */
	@Column(name = "password", nullable = false)
	private String password;

	/**
	 * Default constructor.
	 */
	public User() {
	}

	/**
	 * Constructor.
	 *
	 * @param username username
	 * @param password password
	 */
	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}

	/**
	 * Gets username.
	 *
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Sets username.
	 *
	 * @param username the username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Gets password.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets password.
	 *
	 * @param password the password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
