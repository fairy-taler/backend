package com.fairytaler.fairytalecat.exception;

public class BlockFailedException extends RuntimeException {
    public BlockFailedException() {
        super();
    }
    public BlockFailedException(String s) {
        super(s);
    }
    public BlockFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public BlockFailedException(Throwable cause) {
        super(cause);
    }
}
