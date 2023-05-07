package com.sshukla.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
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
public class SecurityConfig {
	/**
	 * This method creates a UserDetailsService bean to load user details from memory.
	 *
	 * @return the UserDetailsService bean
	 */
	//Authentication
	@Bean
	public UserDetailsService userDetailsService() {
		return new InMemoryUserDetailsManager(new CustomUserDetailsService().loadUserByUsername("admin@admin.com"),
				new CustomUserDetailsService().loadUserByUsername("user@user.com"));
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
				.requestMatchers("/api/users/welcome").permitAll()
				.and()
				.authorizeHttpRequests()
				.requestMatchers("/api/users/**")
				.authenticated()
				.and()
				.formLogin()
				.and()
				.build();

	}
}
