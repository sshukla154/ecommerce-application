package com.sshukla.config;

import com.sshukla.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Created by `Seemant Shukla` on 07-05-2023
 */

/**
 * This class represents the security configuration for a web application.
 * It enables web security and configures authentication and authorization.
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

	private final CustomUserDetailsService customUserDetailsService;

	private final PasswordConfig passwordConfig;

	public SecurityConfig(CustomUserDetailsService customUserDetailsService, PasswordConfig passwordConfig) {
		this.customUserDetailsService = customUserDetailsService;
		this.passwordConfig = passwordConfig;
	}

	/**
	 * This method creates a UserDetailsService bean to load user details from memory.
	 *
	 * @return the UserDetailsService bean
	 */
	//Authentication
	@Bean
	public UserDetailsService userDetailsService() {
		return customUserDetailsService;
	}

	/**
	 * This method configures the security filter chain for the web application.
	 *
	 * @param http the HttpSecurity object to configure the filter chain
	 * @return the SecurityFilterChain object representing the configured filter chain
	 * @throws Exception if an exception occurs while configuring the filter chain
	 */
	//Authorization
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		return http.csrf().disable()
				.authorizeHttpRequests()
				.requestMatchers("/api/v1/users/welcome", "/api/v1/users/create", "/api/v1/users/password/**").permitAll()
				.and()
				.authorizeHttpRequests()
				.requestMatchers("/api/v1/users/**")
				.authenticated()
				.and()
				.formLogin()
//				.httpBasic() -- Disabled for Basic Authentication
				.and()
				.build();

	}

	@Bean
	public AuthenticationProvider authenticationProvider(){
		DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService());
		authenticationProvider.setPasswordEncoder(passwordConfig.passwordEncoder());
		return authenticationProvider;
	}

}
