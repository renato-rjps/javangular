package br.com.rsbdev.starter.security.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "The password information is invalid")
public class InvalidPasswordException extends AuthenticationException{

	private static final long serialVersionUID = -8850311363838712731L;

}
