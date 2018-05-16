package br.com.rsbdev.starter.security.jwt;

import static io.jsonwebtoken.SignatureAlgorithm.HS512;
import static java.time.temporal.ChronoUnit.DAYS;

import java.io.Serializable;
import java.time.Instant;
import java.util.Date;

import org.springframework.stereotype.Component;

import br.com.rsbdev.starter.security.exception.InvalidTokenException;
import br.com.rsbdev.starter.util.DateUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

/**
 * Utility component to manipulate JWT.
 * 
 * @author Renato
 *
 */
@Component
public class JwtService implements Serializable {

	private static final long JWT_EXPIRATION_TIME = 30l; // 30 dias;

	private static final String JWT_SECRET = "OssAplicationJWTSecret";

	private static final long serialVersionUID = -3301605591108950415L;

	// TODO: Deverá ser colocada a identificação do Dipositivo que
	// esta tentando autenticar
	static final String CLAIM_KEY_AUDIENCE = "audience";

	/**
	 * Generate a JWT.
	 * 
	 * @param user
	 *            {@link JwtUser} implementation
	 * 
	 * @return JWT
	 */
	public String generateToken(JwtUser user) {
		Instant created = DateUtil.instantNow();
		Instant expirationDate = created.plus(JWT_EXPIRATION_TIME, DAYS);
		return Jwts
				.builder()
				.setSubject(user.getUsername())
				.setIssuedAt(Date.from(created))
				.setExpiration(Date.from(expirationDate))
				.signWith(HS512, JWT_SECRET)
				.compact();
	}

	/**
	 * Retrieves the subject from token.
	 * 
	 * @param token
	 *            the JWT
	 * @return the subject.
	 */
	public String getTokenSubject(String token) {
		Claims claims = getClaimsFromToken(token);
		
		return claims != null ? claims.getSubject() : null;
	}

	/**
	 * Retrieves the token created date.
	 * 
	 * @param token
	 *            the JWT
	 * @return The date of the token was created.
	 */
	public Instant getTokenCreatedDate(String token) {
		final Claims claims = getClaimsFromToken(token);
		Date created = claims.getIssuedAt();
		return created.toInstant();
	}

	/**
	 * Retrieves the token expiration date.
	 * 
	 * @param token
	 *            the JWT
	 * @return The date of the token will be expired.
	 */
	public Instant getTokenExpirationDate(String token) {
		final Claims claims = getClaimsFromToken(token);
		Date expiration = claims.getExpiration();
		return expiration.toInstant();
	}

	/**
	 * Verify if the token passed as argument can be refreshed
	 * @param token
	 * @param user
	 * @return
	 */
	public Boolean canTokenBeRefreshed(final String token, final JwtUser user) {
		return isTokenValid(token, user);
	}

	/**
	 * Verify if the token passed as argument is valid
	 * 
	 * @param token
	 * @param user
	 */
	public void validateToken(final String token, final JwtUser user) {
		if (!isTokenValid(token, user)) {
			throw new InvalidTokenException(String.format("Invalid token: %s", token));
		}
	}

	private Claims getClaimsFromToken(String token) {
		return Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(token).getBody();
	}

	private Boolean isTokenValid(final String token, final JwtUser user) {
		final String username = getTokenSubject(token);
		final Instant created = getTokenCreatedDate(token);
		final Instant expiration = getTokenExpirationDate(token);

		return username.equals(user.getUsername()) && expiration.isAfter(DateUtil.instantNow())
				&& created.isAfter(user.getLastPasswordResetDate());
	}

}
