package br.com.rsbdev.starter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.rsbdev.starter.model.User;
import br.com.rsbdev.starter.repository.UserRepository;
import br.com.rsbdev.starter.security.exception.UserNotFoundException;

/**
 * User Service
 * 
 * @author Renato
 *
 */
@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public User getByEmail(String email) {
		return userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException("email", email));
	}

	public User getByName(String name) {
		return userRepository.findByName(name).orElseThrow(() -> new UserNotFoundException("name", name));
	}

	@Transactional
	public User add(User user) {
		return userRepository.saveAndFlush(user);
	}

	public User getReference(Long id) {
		return userRepository.getOne(id);
	}

}