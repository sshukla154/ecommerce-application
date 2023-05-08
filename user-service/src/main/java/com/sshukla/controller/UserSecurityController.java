package com.sshukla.controller;


import com.sshukla.entity.User;
import com.sshukla.repository.UserRepo;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

	@PostMapping("/create")
	@PreAuthorize("hasAuthority('ECOMMERCE_ADMIN') or hasAuthority('ECOMMERCE_USER')")
	public ResponseEntity<User> createUser(@RequestBody User user) {
		LOGGER.info("Creating user: {}", user.toString());
		user.setId(UUID.randomUUID().toString());
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.getRoles().forEach(System.out::println);
		return ResponseEntity.ok(userRepo.save(user));
	}

}
