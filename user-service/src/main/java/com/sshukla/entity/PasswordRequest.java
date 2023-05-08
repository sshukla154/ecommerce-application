package com.sshukla.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by `Seemant Shukla` on 08-05-2023
 */

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PasswordRequest {
	private String username;
	private String oldPassword;
	private String newPassword;
}
