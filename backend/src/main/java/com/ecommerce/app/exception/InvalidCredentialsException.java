package com.ecommerce.app.exception;

/**
 * Thrown when login credentials are invalid.
 * Mapped to HTTP 401 by GlobalExceptionHandler.
 */
public class InvalidCredentialsException extends RuntimeException {
    public InvalidCredentialsException(String message) {
        super(message);
    }
}
