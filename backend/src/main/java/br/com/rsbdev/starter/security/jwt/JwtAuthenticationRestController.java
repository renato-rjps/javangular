package br.com.rsbdev.starter.security.jwt;

import org.hibernate.boot.model.naming.IllegalIdentifierException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.fasterxml.jackson.core.JsonProcessingException;

import br.com.rsbdev.starter.controller.AbstractRestController;
import br.com.rsbdev.starter.security.exception.InvalidPasswordException;
import br.com.rsbdev.starter.security.exception.InvalidTokenException;

@RestController
@RequestMapping("/auth")
public class JwtAuthenticationRestController extends AbstractRestController {

	@Autowired
	private JwtService jwtTokenUtil;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private JwtAuthenticationService authenticationService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	/**
	 * Entry point to authenticate an user
	 * 
	 * @param credentials
	 *            object with username and password
	 * @return response with authentication JWT
	 * @throws JsonProcessingException
	 */
	@PostMapping("/login")
	public JwtAuthenticationResponse getAuthenticationToken(@RequestBody JwtAuthenticationCredentials credentials) {

		LOGGER.debug("[POST] User authentication process started with user: {}", credentials.getUsername());

		JwtUser user = this.authenticationService.fetchUserDetails(credentials);

		if (!passwordEncoder.matches(credentials.getPassword(), user.getPassword())) {
			throw new InvalidPasswordException();
		}

		return this.authenticationService.generateJwtAutenticatinResponse(user);
	}

	@PostMapping("/refresh")
	public JwtAuthenticationResponse refreshAndGetAuthenticationToken(@RequestParam String token) {

		if (token == null || token.isEmpty()) {
			throw new IllegalIdentifierException("The token param should be informed");
		}

		String username = jwtTokenUtil.getTokenSubject(token);
		JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(username);

		if (jwtTokenUtil.canTokenBeRefreshed(token, user)) {
			return this.authenticationService.generateJwtAutenticatinResponse(user);
		}

		throw new InvalidTokenException("Token expirado");
	}

	/**
	 * Get the current authenticated user
	 * 
	 * @param authentication
	 *            injectable by spring
	 * @return {@link Authentication} implementation with principal
	 */
	@GetMapping
	//@Authorize("USER")
	public Authentication getCurrentAuthenticationUser(Authentication authentication) {
		//return SecurityContextHolder.getContext().getAuthentication();
		return authentication;
	}

	/**
	 * Handle authentication error messages.
	 * 
	 * @param exception
	 * @param request
	 * @return response with error response @see {@link AuthenticationErrorResponse}
	 */
	@ExceptionHandler({ HttpMessageNotReadableException.class })
	public ResponseEntity<Object> handleException(Exception exception, WebRequest request) {

		String errorMessage = exception.getMessage();

		if (exception instanceof HttpMessageNotReadableException) {
			errorMessage = "Body Should be Specified";
		}

		LOGGER.warn(errorMessage);
		return new ResponseEntity<Object>(errorMessage, new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}

}