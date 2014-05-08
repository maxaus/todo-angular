package com.baev.todolist.domain.repository;

import com.baev.todolist.BasePersistenceTest;
import com.baev.todolist.domain.model.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * {@link UserRepository} test.
 *
 * @author Maxim Baev
 */
public class UserRepositoryTest extends BasePersistenceTest {

	private static final String TEST_USERNAME = "sa";

	@Autowired
	private UserRepository userRepository;

	@Test
	public void testFindByUsername() {
		User user = userRepository.findByUsername(TEST_USERNAME);
		assertNotNull(user);
		assertEquals(TEST_USERNAME, user.getUsername());
	}
}
