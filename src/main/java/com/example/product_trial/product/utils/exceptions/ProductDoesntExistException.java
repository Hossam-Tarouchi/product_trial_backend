package com.example.product_trial.product.utils.exceptions;

public class ProductDoesntExistException extends RuntimeException {
    public ProductDoesntExistException(String message) {
        super(message);
    }
}
