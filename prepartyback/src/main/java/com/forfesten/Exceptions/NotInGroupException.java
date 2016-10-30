package com.forfesten.Exceptions;

/**
 * Exception when a user is not in a group.
 */
public class NotInGroupException extends Exception {

    public NotInGroupException() {
        super("Not in group.");
    }
}
