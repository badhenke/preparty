package com.forfesten.Exceptions;

/**
 * Exception when user does not have an invite.
 */
public class NoInviteException extends Exception {

    public NoInviteException() {
        super("User does not have an invite.");
    }
}
