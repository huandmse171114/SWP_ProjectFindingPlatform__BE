package com.findhub.finhubbackend.exception;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(String message) {
        super(message);
    }

    public EntityNotFoundException(String entityName, int id) {
        super("Cannot find " + entityName + "[id=" + id + "]");
    }

    public EntityNotFoundException(Class<?> entity, int id) {
        super("Cannot find " + entity.getSimpleName() + "[id=" + id + "]");
    }

    public EntityNotFoundException(Class<?> entity) {
        super("Cannot find " + entity.getSimpleName());
    }

    public EntityNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}