package br.com.rsbdev.starter.security.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception para tokens iválido.
 * 
 * @author Renato
 *
 */
@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class InvalidTokenException extends AuthorizationException {

	private static final long serialVersionUID = 1L;

	private static final String MESSAGE = "Invalid Token";
	
	public InvalidTokenException() {
		super(MESSAGE);
	}
	
	public InvalidTokenException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidTokenException(String message) {
		super(message);
	}

}
