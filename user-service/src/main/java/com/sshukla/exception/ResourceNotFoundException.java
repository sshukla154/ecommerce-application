package com.sshukla.exception;

/**
 * Created by `Seemant Shukla` on 08-05-2023
 */

public class ResourceNotFoundException extends RuntimeException {
	public ResourceNotFoundException(String message) {
		super(message);
	}
}
