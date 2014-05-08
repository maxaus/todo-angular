package com.baev.todolist.controller.rest;

import com.baev.todolist.domain.model.User;
import com.baev.todolist.service.AuthenticationService;
import com.baev.todolist.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * UI controller to manage user acccounts.
 *
 * @author Maxim Baev
 */
@Controller
@RequestMapping(value = "/api/account", produces = "application/json; charset=utf-8")
public class AccountController {

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
	 * Retrieves current user name.
	 *
	 * @return Logged in user name.
	 */
	@RequestMapping(value = "/getUserName", method = RequestMethod.POST)
	@ResponseBody
	public String getUserName() {
		User user = authenticationService.getCurrentUser();
		return user.getUsername();
	}
}
