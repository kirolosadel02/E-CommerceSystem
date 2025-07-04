package com.fawry.ecommerce.exception;

public class ProductExpiredException extends RuntimeException {
    public ProductExpiredException(String message) {
        super(message);
    }
}
