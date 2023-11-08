package com.pongtayot.restapi_hibernet.exception;

public class UserDuplicateException extends RuntimeException {
    public UserDuplicateException(String username) {
        super("Username: " + username + " already exists.");
    }
}
