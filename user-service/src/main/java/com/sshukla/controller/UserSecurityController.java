package com.sshukla.controller;


import com.sshukla.entity.PasswordRequest;
import com.sshukla.entity.User;
import com.sshukla.repository.UserRepo;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

	@PostMapping("/password")
	public ResponseEntity<HttpStatus> updatePassword(@RequestBody PasswordRequest passwordRequest) {
		LOGGER.info("Update password for user : {}", passwordRequest.getUsername());
		Optional<User> savedUser = userRepo.findByUsername(passwordRequest.getUsername());
		savedUser.ifPresent(user ->  {
			if (passwordRequest.getNewPassword() != null && passwordRequest.getNewPassword().length() != 0) {
				savedUser.get().setPassword(bCryptPasswordEncoder.encode(passwordRequest.getNewPassword()));
				userRepo.save(savedUser.get());
			} else {
				new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
		});
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PostMapping("/create")
//	@Secured("hasAuthority('ECOMMERCE_ADMIN') or hasAuthority('ECOMMERCE_USER')")
	public ResponseEntity<User> createUser(@RequestBody User user) {
		LOGGER.info("Creating user: {}", user.toString());
		user.setId(UUID.randomUUID().toString());
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
//		user.getRoles().forEach(System.out::println);
		return ResponseEntity.ok(userRepo.save(user));
	}

	@GetMapping("/all")
	@PreAuthorize("hasAuthority('ECOMMERCE_ADMIN')")
	public ResponseEntity<List<User>> getAll() {
		LOGGER.info("Getting All Users");
		return ResponseEntity.ok(userRepo.findAll());
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('ECOMMERCE_USER')")
	public ResponseEntity<User> getUser(@PathVariable String id) {
		LOGGER.info("Getting user with ID: {}", id);
		return ResponseEntity.ok(userRepo.findById(id).get());
	}

}
