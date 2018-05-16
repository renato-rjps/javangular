package br.com.rsbdev.starter.security.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.rsbdev.starter.security.exception.InvalidTokenException;

/**
 * Filtro personalizado para autenticação via JWT. This filter will be run
 * before {@link UsernamePasswordAuthenticationFilter}
 * 
 * @author Renato
 *
 */
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	private static final String HIDE_CREDENTIALS = "HIDE_CREDENTIALS";

	private static final String TOKEN_PREFIX = "Bearer ";

	private static final String AUTHORIZATION_HEADER = "Authorization";

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private JwtService jwtTokenUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {

		String header = request.getHeader(AUTHORIZATION_HEADER);

		if (header == null || !header.startsWith(TOKEN_PREFIX)) {
			chain.doFilter(request, response);
			return;
		}

		try {
			final String token = extractTokenFromHeader(header);

			final String username = jwtTokenUtil.getTokenSubject(token);

			JwtUser jwtUser = (JwtUser) this.userDetailsService.loadUserByUsername(username);

			jwtTokenUtil.validateToken(token, jwtUser);

			UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(jwtUser,
					HIDE_CREDENTIALS, jwtUser.getAuthorities());
			authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			SecurityContextHolder.getContext().setAuthentication(authentication);
			chain.doFilter(request, response);

		} catch (InvalidTokenException exception) {
			SecurityContextHolder.clearContext();
			response.sendError(HttpServletResponse.SC_CONFLICT, "Teste");
			// TODO: Tratar erros de autenticação
			// ResponseWrapper responseWrapper = new ResponseWrapper(response, "UTF-8");
			// response.setHeader(HttpHeaders.CONTENT_TYPE,
			// MediaType.APPLICATION_JSON_UTF8_VALUE);
			// response.getWriter().write("Authentication Error");
			return;
		}
		chain.doFilter(request, response);
	}

	private String extractTokenFromHeader(String header) {
		return header.substring(TOKEN_PREFIX.length());
	}

}