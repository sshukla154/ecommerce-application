package com.sshukla.controller;


import com.sshukla.entity.User;
import com.sshukla.repository.UserRepo;
import com.sshukla.service.CustomUserDetailsService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Created by `Seemant Shukla` on 07-05-2023
 */

@RestController
@Validated
@Api(value = "User Management", tags = "User Management")
@RequestMapping("/api/v1/users")
public class UserSecurityController {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private CustomUserDetailsService customUserDetailsService;

//	@Autowired
//	private BCryptPasswordEncoder passwordEncoder;

	@GetMapping("/welcome")
	public String showWelcomePage(Model model) {
		model.addAttribute("message", "Welcome to my Spring Boot application!");
		return "Welcome";
	}

	@PostMapping("/create")

	public ResponseEntity<User> createUser(@RequestBody User user) {
//		String encodedPassword = passwordEncoder.encode(user.getPassword());
//		user.setPassword(encodedPassword);
		return ResponseEntity.ok(userRepo.save(user));
	}

}
