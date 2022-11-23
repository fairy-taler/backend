package com.fairytaler.fairytalecat.exception;

public class AuthorizationException extends RuntimeException {
    public AuthorizationException() {
        super();
    }
    public AuthorizationException(String s) {
        super(s);
    }
    public AuthorizationException(String message, Throwable cause) {
        super(message, cause);
    }

    public AuthorizationException(Throwable cause) {
        super(cause);
    }
}
