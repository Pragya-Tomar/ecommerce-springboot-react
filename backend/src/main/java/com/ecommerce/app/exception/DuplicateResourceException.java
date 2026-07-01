package com.ecommerce.app.exception;

/**
 * Thrown when trying to create a resource that violates a uniqueness rule
 * (e.g. registering with a username or email that's already taken).
 * Mapped to HTTP 409 by GlobalExceptionHandler.
 */
public class DuplicateResourceException extends RuntimeException {
    public DuplicateResourceException(String message) {
        super(message);
    }
}
