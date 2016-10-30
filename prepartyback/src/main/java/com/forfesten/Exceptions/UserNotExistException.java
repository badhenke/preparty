package com.forfesten.Exceptions;

/**
 * Exception when user not exists
 */
public class UserNotExistException extends Exception {

    public UserNotExistException() {
        super("User does not exist.");
    }
}
