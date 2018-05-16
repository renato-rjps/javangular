package br.com.rsbdev.starter.security.jwt;

import static java.lang.String.format;

import java.io.Serializable;

import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * JWT authentication response.
 * 
 * @author Renato
 *
 */
@EqualsAndHashCode
@Getter
public class JwtAuthenticationResponse implements Serializable {

	private static final long serialVersionUID = 1250166508152483573L;
	private final String token;

	public JwtAuthenticationResponse(final String token) {
		this.token = token;
	}

	@Override
	public String toString() {
		return format("{\"token\":\"%s\"}", this.token);
	}
}