package com.sshukla.config;

import com.sshukla.entity.User;
import com.sshukla.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

/**
 * Created by `Seemant Shukla` on 07-05-2023
 */

@Service
@Transactional
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	UserRepo userRepo;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User savedUser = userRepo.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username not found"));
		return new UserDetails() {
			@Override
			public Collection<? extends GrantedAuthority> getAuthorities() {
				return savedUser.getRole();
			}

			@Override
			public String getPassword() {
				return passwordEncoder.encode(savedUser.getPassword());
			}

			@Override
			public String getUsername() {
				return savedUser.getUsername();
			}

			@Override
			public boolean isAccountNonExpired() {
				return savedUser.isEnabled();
			}

			@Override
			public boolean isAccountNonLocked() {
				return savedUser.isEnabled();
			}

			@Override
			public boolean isCredentialsNonExpired() {
				return savedUser.isEnabled();
			}

			@Override
			public boolean isEnabled() {
				return savedUser.isEnabled();
			}
		};
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
