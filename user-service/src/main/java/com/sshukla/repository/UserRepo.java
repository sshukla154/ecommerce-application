package com.sshukla.repository;

import com.sshukla.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created by `Seemant Shukla` on 07-05-2023
 */

//@Repository
public interface UserRepo extends JpaRepository<User, String> {
	Optional<User> findByUsername(String username);
}
