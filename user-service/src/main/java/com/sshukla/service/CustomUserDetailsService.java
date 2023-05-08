package com.sshukla.service;

import com.sshukla.entity.User;
import com.sshukla.repository.UserRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;

/**
 * Created by `Seemant Shukla` on 07-05-2023
 */


/**
 * This class represents a custom user details service for authentication and authorization.
 * It implements the UserDetailsService interface and provides methods to load user details from the database.
 */
@Service
@Transactional
public class CustomUserDetailsService implements UserDetailsService {

	private final UserRepo userRepo;

	public CustomUserDetailsService(UserRepo userRepo) {
		this.userRepo = userRepo;
	}

	/**
	 * This method loads user details by the given username.
	 *
	 * @param username the username of the user to load
	 * @return the UserDetails object representing the loaded user
	 * @throws UsernameNotFoundException if the username is not found in the database
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User savedUser = userRepo.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username not found"));
		return new CustomUserDetails(savedUser);
	}

	/**
	 * This method creates a PasswordEncoder bean to encode passwords.
	 *
	 * @return the PasswordEncoder bean
	 */


	public User getLoggedInUser(Principal principal) {
		return userRepo.findByUsername(principal.getName()).get();
	}

}
