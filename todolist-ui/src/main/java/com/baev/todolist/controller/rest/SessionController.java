package com.baev.todolist.controller.rest;

import com.baev.todolist.controller.rest.dto.UserCredentials;
import com.baev.todolist.domain.model.User;
import com.baev.todolist.service.AuthenticationService;
import com.baev.todolist.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * UI controller to manage users.
 *
 * @author Maxim Baev
 */
@Controller
@RequestMapping(value = "/api/session", produces = "application/json; charset=utf-8")
public class SessionController {

	/**
	 * Instance of AuthenticationManager
	 */
	@Autowired
	private AuthenticationManager authenticationManager;

	/**
	 * Authentication info service.
	 */
	@Autowired
	private AuthenticationService authenticationService;

	/**
	 * User service.
	 */
	@Autowired
	private UserService userService;

	/**
	 * This operation creates a new user and authenticates user with this account.
	 *
	 * @param userCredentials user credentials
	 */
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> register(@RequestBody UserCredentials userCredentials) {
		User account = new User();
		account.setPassword(userCredentials.getPassword());
		account.setUsername(userCredentials.getUsername());
		userService.create(account);
		return logIn(userCredentials);
	}

	/**
	 * Checks if user is logged in.
	 *
	 * @return True if user is logged in
	 */
	@RequestMapping(value = "/isLoggedIn", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> isLoggedIn() {
		Map<String, Object> res = new HashMap<>();
		res.put("isLoggedIn", authenticationService.getCurrentUser() != null);
		return res;
	}

	/**
	 * This operation authenticates user in the current user session.
	 *
	 * @param credentials credentials
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> logIn(@RequestBody UserCredentials credentials) {
		Map<String, Object> res = new HashMap<>();
		springLogin(credentials.getUsername(), credentials.getPassword());
		res.put("hasLoggedIn", true);
		return res;
	}

	/**
	 * This method is used by the client to log out of the application.
	 *
	 * @param session session
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	@ResponseBody
	public void logOut(HttpSession session) {
		SecurityContextHolder.clearContext();
		if (session != null) {
			session.invalidate();
		}
	}

	/**
	 * This method performs spring authentication.
	 *
	 * @param email    email
	 * @param password password
	 */
	private void springLogin(String email, String password) {
		Authentication token = new UsernamePasswordAuthenticationToken(email, password);
		Authentication authentication = authenticationManager.authenticate(token);
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}
}
