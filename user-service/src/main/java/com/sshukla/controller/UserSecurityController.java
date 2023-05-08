package com.sshukla.controller;


import com.sshukla.entity.PasswordRequest;
import com.sshukla.entity.User;
import com.sshukla.exception.ResourceNotFoundException;
import com.sshukla.repository.UserRepo;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Created by `Seemant Shukla` on 07-05-2023
 */

@RestController
@Validated
@Api(value = "User Management", tags = "User Management")
@RequestMapping("/api/v1/users")
public class UserSecurityController {

	Logger LOGGER = LoggerFactory.getLogger(UserSecurityController.class);

	private final UserRepo userRepo;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	public UserSecurityController(UserRepo userRepo, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.userRepo = userRepo;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@GetMapping("/welcome")
	public String showWelcomePage(Model model) {
		model.addAttribute("message", "Welcome to my Spring Boot application!");
		return "welcome";
	}

	/**
	 * This method updates the password for a user.
	 *
	 * @param passwordRequest the request containing the user's old and new passwords
	 * @return a ResponseEntity with HTTP status OK if the password is updated successfully, or BAD_REQUEST if the new password is invalid
	 */
	@PostMapping("/password")
	public ResponseEntity<HttpStatus> updatePassword(@RequestBody PasswordRequest passwordRequest) {
		LOGGER.info("Update password for user : {}", passwordRequest.getUsername());
		userRepo.findByUsername(passwordRequest.getUsername())
				.map(user -> {
					String newPassword = passwordRequest.getNewPassword();
					if (isValidPassword(newPassword)) {
						user.setPassword(bCryptPasswordEncoder.encode(newPassword));
						return userRepo.save(user);
					} else {
						throw new IllegalArgumentException("New password is invalid.");
					}
				})
				.orElseThrow(() -> new UsernameNotFoundException("User not found."));

		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@PostMapping("/create")
//	@Secured("hasAuthority('ECOMMERCE_ADMIN') or hasAuthority('ECOMMERCE_USER')")
	public ResponseEntity<User> createUser(@RequestBody User user) {
		LOGGER.info("Creating user: {}", user.getUsername());
		user.setId(UUID.randomUUID().toString());
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		return ResponseEntity.ok(userRepo.save(user));
	}

	/**
	 * Returns a ResponseEntity containing a List of all the Users in the system.
	 * The endpoint is accessible only to users with the authority 'ECOMMERCE_ADMIN'.
	 *
	 * @return ResponseEntity<List < User>> containing a list of all users
	 */
	@GetMapping("/all")
	@PreAuthorize("hasAuthority('ECOMMERCE_ADMIN')")
	public ResponseEntity<List<User>> getAll() {
		LOGGER.info("Getting All Users");
		List<User> allUsers = userRepo.findAll();
		return ResponseEntity.ok(allUsers);
	}

	/**
	 * Returns a ResponseEntity containing a User with the specified id.
	 * The endpoint is accessible only to users with the authority 'ECOMMERCE_USER'.
	 *
	 * @param id the id of the user to retrieve
	 * @return ResponseEntity<User> containing the user with the specified id
	 * @throws ResourceNotFoundException if the user with the specified id is not found
	 */
	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('ECOMMERCE_USER')")
	public ResponseEntity<User> getUser(@PathVariable String id) throws ResourceNotFoundException {
		LOGGER.info("Getting user with ID: {}", id);
		Optional<User> user = userRepo.findById(id);
		if (user.isPresent()) {
			return ResponseEntity.ok(user.get());
		} else {
			throw new ResourceNotFoundException("User with id " + id + " not found.");
		}
	}

	private boolean isValidPassword(String password) {
		return password != null && !password.isEmpty();
	}

}
