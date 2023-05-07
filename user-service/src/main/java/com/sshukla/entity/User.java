package com.sshukla.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

/**
 * Created by `Seemant Shukla` on 07-05-2023
 */

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity {

	private String firstName;
	private String lastName;
	private String username;
	private String password;
	private String email;
	private List<Role> role;
	private boolean enabled;

}
