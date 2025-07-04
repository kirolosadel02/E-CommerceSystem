package com.fawry.ecommerce.exception;

public class ProductBuilderException extends RuntimeException {
    public ProductBuilderException(String message) {
        super(message);
    }

    public ProductBuilderException(String message, Throwable cause) {
        super(message, cause);
    }
} 