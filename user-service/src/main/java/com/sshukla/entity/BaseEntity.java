package com.sshukla.entity;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

import java.time.LocalDateTime;

/**
 * Created by `Seemant Shukla` on 07-05-2023
 */

@MappedSuperclass
public class BaseEntity {

    @Id
    private String id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
