package com.fairytaler.fairytalecat.exception;

public class UpdateFailedException extends RuntimeException {
    public UpdateFailedException() {
        super();
    }
    public UpdateFailedException(String s) {
        super(s);
    }
    public UpdateFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public UpdateFailedException(Throwable cause) {
        super(cause);
    }
}
