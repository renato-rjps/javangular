package br.com.rsbdev.starter.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * RSBdev generic exception
 * 
 * @author Renato
 *
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RSBdevException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public RSBdevException() {
		super();
	}

	public RSBdevException(String message, Throwable cause) {
		super(message, cause);
	}

	public RSBdevException(String message) {
		super(message);
	}

	public RSBdevException(Throwable cause) {
		super(cause);
	}
}
