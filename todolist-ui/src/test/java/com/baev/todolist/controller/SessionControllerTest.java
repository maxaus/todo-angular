package com.baev.todolist.controller;

import com.baev.todolist.controller.rest.SessionController;
import com.baev.todolist.controller.rest.dto.UserCredentials;
import com.baev.todolist.domain.model.User;
import com.baev.todolist.service.AuthenticationService;
import com.baev.todolist.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import java.util.Map;

import static junit.framework.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * Tests {@link SessionController} instance.
 *
 * @author Maxim Baev
 */
public class SessionControllerTest {

	@InjectMocks
	private SessionController sessionController;

	@Mock
	private AuthenticationManager authenticationManager;

	@Mock
	private AuthenticationService authenticationService;

	@Mock
	private UserService userService;

	@Before
	public void setup() {
		initMocks(this);
	}

	@Test
	public void testLogin() {
		Authentication authentication = new UsernamePasswordAuthenticationToken("auth", "path");
		when(authenticationManager.authenticate(any(Authentication.class))).thenReturn(authentication);
		Map<String, Object> result = sessionController.logIn(new UserCredentials());
		assertNotNull(result);
		assertEquals(1, result.size());
	}

	@Test
	public void testRegister() {
		UserCredentials userCredentials = new UserCredentials();
		userCredentials.setUsername("user");
		userCredentials.setPassword("pass");
		User user = new User(userCredentials.getUsername(), userCredentials.getPassword());
		when(userService.create(user)).thenReturn(user);
		Map<String, Object> result = sessionController.register(userCredentials);
		assertNotNull(result);
		assertEquals(1, result.size());
	}

	@Test
	public void testIsLoggedIn() {
		when(authenticationService.getCurrentUser()).thenReturn(null);
		Map<String, Object> result = sessionController.isLoggedIn();
		assertNotNull(result);
		assertFalse((Boolean) result.get("isLoggedIn"));
	}

}
