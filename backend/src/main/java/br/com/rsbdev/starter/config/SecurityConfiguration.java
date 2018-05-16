package br.com.rsbdev.starter.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.com.rsbdev.starter.security.jwt.JwtAuthenticationEntryPoint;
import br.com.rsbdev.starter.security.jwt.JwtAuthenticationFilter;

/**
 * Main Security Configuration class
 * 
 * @author Renato
 *
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class SecurityConfiguration {

	/**
	 * Configuração do spring security.
	 * 
	 * @author Renato
	 */
	@Configuration
	public static class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
		
		  @Autowired
		    private JwtAuthenticationEntryPoint unauthorizedHandler;

		@Autowired
		private UserDetailsService userDetailsService;

		@Autowired
		public void configureAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
			authenticationManagerBuilder.userDetailsService(this.userDetailsService).passwordEncoder(passwordEncoder());
		}

		@Bean
		public PasswordEncoder passwordEncoder() {
			return new BCryptPasswordEncoder();
		}

		@Bean
		public JwtAuthenticationFilter authenticationTokenFilterBean() throws Exception {
			return new JwtAuthenticationFilter();
		}

		@Override
		public void configure(WebSecurity web) throws Exception {
			// Ignore security filter for assets
			web.ignoring()
				.antMatchers(
					"/public/**",					
					"/**/*.html", 
					"/**/*.{png,jpg,jpeg,svg.ico}", 
					"/**/*.css",
					"/**/*.js");
		}

		@Override
		protected void configure(HttpSecurity httpSecurity) throws Exception {
			httpSecurity
				.csrf()
					.disable()
				.exceptionHandling()
					.authenticationEntryPoint(unauthorizedHandler)
			.and()
				.sessionManagement()
					.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
				.authorizeRequests()
					.antMatchers("/","/swagger**","/actuator**","/h2-console/**","/v2/api-docs**","/auth/**").permitAll()
					.anyRequest().authenticated();

			// Custom JWT Authentication Filter
			httpSecurity.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);

			// Disable page caching
			httpSecurity.headers().frameOptions().disable().cacheControl().disable();
			
		}
	}
}
