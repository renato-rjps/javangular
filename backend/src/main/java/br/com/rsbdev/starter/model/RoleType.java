package br.com.rsbdev.starter.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

/**
 * Role type definition
 * 
 * @author Renato
 *
 */
public enum RoleType {
	ROLE_ADMIN, ROLE_USER;
	
	public GrantedAuthority asAuthority() {
		return new SimpleGrantedAuthority(this.name());
	}
	
	public String asRole() {
		return this.name().substring("ROLE_".length());
	}
}