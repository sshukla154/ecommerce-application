package com.sshukla.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by `Seemant Shukla` on 07-05-2023
 */

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity {

	private String firstname;
	private String lastname;
	private String username;
	private String password;
	private List<String> roles;
	private boolean enabled;

	/**
	 * @return Returns all the authorities/roles of the user
	 */
	public List<GrantedAuthority> getAllAuthorities() {
		return this.roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
	}

}
