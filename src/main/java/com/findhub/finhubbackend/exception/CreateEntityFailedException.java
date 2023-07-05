package com.findhub.finhubbackend.exception;

public class CreateEntityFailedException extends RuntimeException {

    public CreateEntityFailedException() {
        super();
    }

    public CreateEntityFailedException(String message) {
        super(message);
    }

    public CreateEntityFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}
