package com.vodafone.webapp.exceptions;

public class UniquenessViolationException extends Exception {

    public UniquenessViolationException() {
        super("User Already Registered Before");
    }
}
