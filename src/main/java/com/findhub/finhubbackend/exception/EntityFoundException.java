package com.findhub.finhubbackend.exception;

public class EntityFoundException extends RuntimeException {

    public EntityFoundException(String message) {
        super(message);
    }

    public EntityFoundException(String entityName, int id) {
        super("Found " + entityName + "[id=" + id + "]");
    }

    public EntityFoundException(Class<?> entity, int id) {
        super("Found " + entity.getSimpleName() + "[id=" + id + "]");
    }

    public EntityFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}