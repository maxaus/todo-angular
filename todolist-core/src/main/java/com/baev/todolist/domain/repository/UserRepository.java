package com.baev.todolist.domain.repository;

import com.baev.todolist.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * User repository.
 *
 * @author Maxim Baev
 */
@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long> {

	/**
	 * Get user by username.
	 *
	 * @param username username
	 * @return User
	 */
	User findByUsername(String username);
}
