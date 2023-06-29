package com.findhub.finhubbackend.exception;

public class StatusNotFoundException extends RuntimeException {
    public StatusNotFoundException() {
        super();
    }

    public StatusNotFoundException(String message) {
        super(message);
    }

    public StatusNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
