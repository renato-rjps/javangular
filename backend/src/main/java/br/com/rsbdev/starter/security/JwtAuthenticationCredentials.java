package br.com.rsbdev.starter.security;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Representação das credentiais do usuário para autenticação utilizando o
 * padrão JWT.
 * 
 * @author Renato
 *
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class JwtAuthenticationCredentials implements Serializable {

	private static final long serialVersionUID = -8445943548965154778L;

	private String username;
	private String password;

}