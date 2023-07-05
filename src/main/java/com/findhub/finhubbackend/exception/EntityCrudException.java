package com.findhub.finhubbackend.exception;

public class EntityCrudException extends RuntimeException {

    public EntityCrudException() {
        super();
    }

    public EntityCrudException(String message) {
        super(message);
    }

    public EntityCrudException(String type, String entityName, int id) {

        switch (type.toLowerCase()) {
            case "create":
                // EntityCrudException();
                break;

            case "read":
                break;
            case "update":

                break;
            case "delete":

                break;

            default:
                break;
        }
    }

    public EntityCrudException(String message, Throwable cause) {
        super(message, cause);
    }
}
