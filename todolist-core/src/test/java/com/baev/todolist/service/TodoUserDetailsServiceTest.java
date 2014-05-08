package com.baev.todolist.service;

import com.baev.todolist.BaseMockitoTest;
import com.baev.todolist.domain.model.User;
import com.baev.todolist.domain.repository.UserRepository;
import com.baev.todolist.service.impl.UserDetailsServiceImpl;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

/**
 * {@link TodoUserDetailsServiceTest) test.
 *
 * @author Maxim Baev
 */
public class TodoUserDetailsServiceTest extends BaseMockitoTest {

	private static final String TEST_USERNAME = "sa";
	@InjectMocks
	private UserDetailsServiceImpl userDetailsService;

	@Mock
	private UserRepository userRepository;

	@Test
	public void testFindByUsername() {
		when(userRepository.findByUsername(TEST_USERNAME)).thenReturn(new User());
		User result = userDetailsService.findByUsername(TEST_USERNAME);
		assertNotNull(result);
	}
}
