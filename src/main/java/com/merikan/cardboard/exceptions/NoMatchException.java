package com.merikan.cardboard.exceptions;

class NoMatchException extends RuntimeException {

    public NoMatchException(String message) {
        super(message);
    }

    public NoMatchException(String message, Throwable cause) {
        super(message, cause);
    }
}
