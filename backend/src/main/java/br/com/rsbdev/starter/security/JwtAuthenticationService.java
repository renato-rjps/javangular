package br.com.rsbdev.starter.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;


/**
 * Serviço para manipular informações do usuário autenticado.
 */
@Service
public class JwtAuthenticationService {

	@Autowired
	private JwtService jwtTokenUtil;

	@Autowired
	private UserDetailsService userDetailsService;

	public JwtUser fetchUserDetails(JwtAuthenticationCredentials credentials) {
		return (JwtUser) this.userDetailsService.loadUserByUsername(credentials.getUsername());
	}

	public JwtAuthenticationResponse generateJwtAutenticatinResponse(JwtUser user) {
		final String token = jwtTokenUtil.generateToken(user);
		return new JwtAuthenticationResponse(token);
	}

}
