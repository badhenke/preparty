package com.forfesten.Exceptions;

/**
 * Exception when a user is already in group
 */
public class AlreadyInGroupException extends Exception {

    public AlreadyInGroupException() {
        super("The user is already in that group.");
    }

}
