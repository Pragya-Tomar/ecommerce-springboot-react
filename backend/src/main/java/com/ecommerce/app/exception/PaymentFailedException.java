package com.ecommerce.app.exception;

/**
 * Thrown when a Razorpay payment fails or signature verification fails.
 * Mapped to HTTP 402 by GlobalExceptionHandler.
 */
public class PaymentFailedException extends RuntimeException {
    public PaymentFailedException(String message) {
        super(message);
    }
}
