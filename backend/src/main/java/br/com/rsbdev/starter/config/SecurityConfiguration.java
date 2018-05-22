package br.com.rsbdev.starter.config;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.com.rsbdev.starter.security.JwtAuthenticationEntryPoint;
import br.com.rsbdev.starter.security.JwtAuthenticationFilter;

/**
 * Main Security Configuration class
 * 
 * @author Renato
 *
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

	@Autowired
	private UserDetailsService userDetailsService;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public JwtAuthenticationFilter authenticationTokenFilterBean() throws Exception {
		return new JwtAuthenticationFilter();
	}
	
	@Autowired
    public void configureAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                .userDetailsService(this.userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

	@Override
	public void configure(WebSecurity web) throws Exception {
		// Ignore security filter for assets
		web.ignoring()
				.antMatchers(
						"/public/**", 
						"/**/*.html", 
						"/**/*.css", 
						"/**/*.js",
						"/**/*.{png,jpg,jpeg,svg.ico}");
	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity
		
			// Não é necessário utilizar CSRF
			.csrf().disable()
			
			.exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and()
			
			// Configura estratégia de sessão stateless
			.sessionManagement().sessionCreationPolicy(STATELESS).and()
			
			// Processamento das requests
			.authorizeRequests()
			
			// Permite solicitação anônima de recursos
			.antMatchers(
					"/", 
					"/auth/**",
					"/swagger**", 
					"/actuator*/**", 
					"/h2-console/**", 
					"/v2/api-docs**"
			).permitAll()
			
			// Bloqueia requests não authenticadas
			.anyRequest().authenticated().and()

			// Filtro customizado para autenticação via JWT
			.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class)
			
			// Desabilita cache de página
			.headers().frameOptions().disable().cacheControl().disable();
	}
}
