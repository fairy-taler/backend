package com.fairytaler.fairytalecat.exception;

public class DuplicatedMemberIdException extends RuntimeException {

    public DuplicatedMemberIdException() {
        super();
    }
    public DuplicatedMemberIdException(String s) {
        super(s);
    }
    public DuplicatedMemberIdException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicatedMemberIdException(Throwable cause) {
        super(cause);
    }
}

