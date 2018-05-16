package br.com.rsbdev.starter.security.exception;

import static java.lang.String.format;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.rsbdev.starter.exception.RSBdevException;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UserNotFoundException extends RSBdevException {

	private static final long serialVersionUID = 1L;

	public UserNotFoundException(String message) {
		super(message);
	}

	public UserNotFoundException(String param, Object value) {
		super(format("There is no user with the following params %s: %s", param, value));
	}

	public UserNotFoundException(Long id) {
		super(format("User not founded with id: %s", id));
	}
}