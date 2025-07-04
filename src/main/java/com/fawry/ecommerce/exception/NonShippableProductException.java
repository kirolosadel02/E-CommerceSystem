package com.fawry.ecommerce.exception;

public class NonShippableProductException extends RuntimeException {
    public NonShippableProductException(String message) {
        super(message);
    }

    public NonShippableProductException(String message, Throwable cause) {
        super(message, cause);
    }
} 