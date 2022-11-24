package com.fairytaler.fairytalecat.exception;

public class TaleException extends RuntimeException {
    public TaleException() {
        super();
    }
    public TaleException(String s) {
        super(s);
    }
    public TaleException(String message, Throwable cause) {
        super(message, cause);
    }

    public TaleException(Throwable cause) {
        super(cause);
    }
}
