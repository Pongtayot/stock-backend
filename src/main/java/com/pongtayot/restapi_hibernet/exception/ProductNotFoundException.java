package com.pongtayot.restapi_hibernet.exception;

public class ProductNotFoundException extends RuntimeException{
    public ProductNotFoundException(long id) {
        super("Could not find product " + id);
    }

    public ProductNotFoundException(String name) {
        super("Could not find product " + name);
    }
}
