package com.sshukla.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by `Seemant Shukla` on 07-05-2023
 */

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity {

    private String username;
    private String password;
    private String email;
    private String role;
    private boolean enabled;

}
