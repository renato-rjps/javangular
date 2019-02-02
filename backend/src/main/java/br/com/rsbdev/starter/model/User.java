package br.com.rsbdev.starter.model;

import java.time.Instant;
import java.util.Collection;
import java.util.Collections;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.rsbdev.starter.security.JwtUser;
import br.com.rsbdev.starter.util.DateUtil;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * User model/entity
 * 
 * @author Renato
 *
 */
@Getter
@Setter
@NoArgsConstructor
@Entity(name = "USUARIO")
@SequenceGenerator(name = "primary_key", sequenceName = "usuario_id_seq", allocationSize = 1)
public class User extends BaseEntity implements JwtUser {

	private static final long serialVersionUID = -3692164635664830066L;

	@JsonIgnore
	@Column(length = 100)
	@NotNull
	@Size(min = 4, max = 100)
	private String password;

	@Column(length = 50)
	@NotNull
	@Size(min = 4, max = 255)
	private String name;
	
	@Column(length = 20)
	@NotNull
	@Size(min = 4, max = 20)
	private String tenant;

	@Column(length = 50, unique = true)
	@NotNull
	@Size(min = 4, max = 255)
	private String email;

	@Column(length = 50, unique = true)
	@NotNull
	@Enumerated(EnumType.STRING)
	private RoleType roleType;

	@NotNull
	private Instant lastPasswordResetDate = DateUtil.instantNow();

	@JsonIgnore
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.singletonList(new SimpleGrantedAuthority(this.getRoleType().name()));
	}

	@Override
	public String getUsername() {
		return email;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public Instant getLastPasswordResetDate() {
		return lastPasswordResetDate;
	}
}
