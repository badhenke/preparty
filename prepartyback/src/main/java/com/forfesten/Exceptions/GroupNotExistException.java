package com.forfesten.Exceptions;

/**
 * Exception when group not exist
 */
public class GroupNotExistException extends Exception{

    public GroupNotExistException() {
        super("Group does not exist.");
    }
}
