package br.com.rsbdev.starter.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.rsbdev.starter.model.User;

/**
 * Repository to manipulate user data.
 * 
 * @author Renato
 *
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	/**
	 * Find user by e-mail
	 * 
	 * @param email
	 * 
	 * @return an {@link User}
	 */
	Optional<User> findByEmail(String email);

	/**
	 * Find user by name
	 * 
	 * @param name
	 * 
	 * @return an {@link User}
	 */
	Optional<User> findByName(String name);
}