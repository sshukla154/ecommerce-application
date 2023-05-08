package com.sshukla.controller;


import com.sshukla.entity.User;
import com.sshukla.repository.UserRepo;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

	@PostMapping("/create")
	public ResponseEntity<User> createUser(@RequestBody User user) {
		return ResponseEntity.ok(userRepo.save(user));
	}

}
