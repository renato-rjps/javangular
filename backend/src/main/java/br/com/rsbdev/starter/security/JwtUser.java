package br.com.rsbdev.starter.security;

import java.time.Instant;

import org.springframework.security.core.userdetails.UserDetails;

/**
 * Interface that represents an JWT User. There are some specifics attributes
 * that will be used for this standard. This extends {@link UserDetails} to
 * integration with spring security.
 * 
 * @author Renato
 *
 */
public interface JwtUser extends UserDetails {

	/**
	 * Database user id
	 * 
	 * @return
	 */
	Long getId();

	/**
	 * User full name
	 * 
	 * @return
	 */
	String getName();

	/**
	 * Last password reset date
	 * 
	 * @return
	 */
	Instant getLastPasswordResetDate();

}
