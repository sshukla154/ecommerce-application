package com.sshukla.controller;


import io.swagger.annotations.Api;
import org.springframework.validation.annotation.Validated;
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
}
