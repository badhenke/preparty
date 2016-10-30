package com.forfesten.Exceptions;

/**
 * Exception when user already has invite
 */
public class AlreadyHasInviteException extends Exception{

    public AlreadyHasInviteException(){
        super("User already has invite.");
    }

}
