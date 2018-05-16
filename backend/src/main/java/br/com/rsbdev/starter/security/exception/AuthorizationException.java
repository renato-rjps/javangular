package br.com.rsbdev.starter.security.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.rsbdev.starter.exception.RSBdevException;

/**
 * Authorization exception
 * 
 * @author Renato
 *
 */
@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class AuthorizationException extends RSBdevException {

	private static final long serialVersionUID = 1L;

	public AuthorizationException() {
		super();
	}

	public AuthorizationException(String message, Throwable cause) {
		super(message, cause);
	}

	public AuthorizationException(String message) {
		super(message);
	}

	public AuthorizationException(Throwable cause) {
		super(cause);
	}
}