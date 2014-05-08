package com.baev.todolist.service;

import com.baev.todolist.BaseMockitoTest;
import com.baev.todolist.domain.model.User;
import com.baev.todolist.domain.repository.UserRepository;
import com.baev.todolist.service.impl.UserServiceImpl;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * {@link UserService} test.
 *
 * @author Maxim Baev
 */
public class UserServiceTest extends BaseMockitoTest {

	@InjectMocks
	private UserServiceImpl userService;

	@Mock
	private UserRepository userRepository;

	@Test
	public void testCreateNewUser() {
		User user = new User();
		user.setUsername("sa");
		user.setPassword("pass");

		when(userRepository.save(user)).thenReturn(user);

		User result = userService.create(user);
		verify(userRepository).save(user);
		assertNotNull(result);
	}
}
