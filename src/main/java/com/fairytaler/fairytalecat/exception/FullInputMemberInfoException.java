package com.fairytaler.fairytalecat.exception;


public class FullInputMemberInfoException extends RuntimeException {

    public FullInputMemberInfoException() {
        super();
    }
    public FullInputMemberInfoException(String s) { super(s);}

    public FullInputMemberInfoException(String message, Throwable cause) {
        super(message, cause);
    }

    public FullInputMemberInfoException(Throwable cause) {
        super(cause);
    }
}
