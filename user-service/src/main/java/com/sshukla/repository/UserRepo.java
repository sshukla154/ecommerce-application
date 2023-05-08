package com.sshukla.repository;

import com.sshukla.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

/**
 * Created by `Seemant Shukla` on 07-05-2023
 */

//@Repository
public interface UserRepo extends JpaRepository<User, String> {
	@Query("SELECT u FROM User u WHERE u.username = :username")
	Optional<User> findByUsername(@Param("username") String username);
}
