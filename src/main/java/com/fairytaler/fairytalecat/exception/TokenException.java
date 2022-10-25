package com.fairytaler.fairytalecat.exception;


public class TokenException extends RuntimeException{

    public TokenException() {
        super();
    }
    public TokenException(String message) {
        super(message);
    }

    public TokenException(String message, Throwable cause) {
        super(message, cause);
    }

    public TokenException(Throwable cause) {
        super(cause);
    }
}
