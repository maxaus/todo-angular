package com.baev.todolist;

import org.junit.Before;

import static org.mockito.MockitoAnnotations.initMocks;

/**
 * Base class for Mockito unit tests.
 *
 * @author Maxim Baev
 */
public abstract class BaseMockitoTest {

	/**
	 * Init.
	 */
	@Before
	public void setUp() {
		initMocks(this);
	}
}
