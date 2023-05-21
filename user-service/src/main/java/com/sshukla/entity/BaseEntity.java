package com.sshukla.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

/**
 * Created by `Seemant Shukla` on 07-05-2023
 */


/**
 * The BaseEntity class is a JPA entity that defines common properties for all entities in the application.
 * It is annotated with @MappedSuperclass to indicate that this class should not be mapped to a database table, but its
 * fields should be inherited by its subclasses.
 */
@MappedSuperclass
@Setter
@Getter
public class BaseEntity {

	@Id
	@Column(name = "id")
	private String id;

	@CreationTimestamp
	private LocalDateTime createdAt;

	@UpdateTimestamp
	private LocalDateTime updatedAt;
}
