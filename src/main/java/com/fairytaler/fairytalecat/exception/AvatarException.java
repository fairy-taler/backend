package com.fairytaler.fairytalecat.exception;

public class AvatarException extends RuntimeException {
    public AvatarException() {
        super();
    }
    public AvatarException(String s) {
        super(s);
    }
    public AvatarException(String message, Throwable cause) {
        super(message, cause);
    }

    public AvatarException(Throwable cause) {
        super(cause);
    }
}
