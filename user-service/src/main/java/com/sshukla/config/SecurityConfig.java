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


@Configuration
@EnableWebSecurity
public class SecurityConfig {

	//Authentication
	@Bean
	public UserDetailsService userDetailsService() {
		return new InMemoryUserDetailsManager(new CustomUserDetailsService().loadUserByUsername("admin@admin.com"),
				new CustomUserDetailsService().loadUserByUsername("user@user.com"));
	}

	//Authorization
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		return http.csrf().disable()
				.authorizeHttpRequests()
				.requestMatchers("/api/products/welcome").permitAll()
				.and()
				.authorizeHttpRequests()
				.requestMatchers("/api/products/**")
				.authenticated()
				.and()
				.formLogin()
				.and()
				.build();

	}
}
