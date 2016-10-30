package com.forfesten.Exceptions;

/**
 * Exception when group is full
 */
public class FullGroupException extends Exception{

    public FullGroupException(){
        super("The group is full.");
    }

}
