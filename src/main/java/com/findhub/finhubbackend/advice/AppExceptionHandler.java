package com.findhub.finhubbackend.advice;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.findhub.finhubbackend.exception.CreateEntityFailedException;
import com.findhub.finhubbackend.exception.EntityCrudException;
import com.findhub.finhubbackend.exception.EntityNotFoundException;
import com.findhub.finhubbackend.exception.StatusNotFoundException;
import com.findhub.finhubbackend.model.model.ResponseModel;

@RestControllerAdvice
public class AppExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseModel handleEntityExisted() {
        return null;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = { EntityNotFoundException.class })
    public ResponseModel handleEntityNotFound(EntityNotFoundException ex) {
        HttpStatus status = HttpStatus.NOT_FOUND;

        return ResponseModel
                .builder()
                .status(status.value())
                .message(ex.getMessage())
                // .cause(ex)
                .build();

        // return response(ex, status);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = { EntityCrudException.class })
    public ResponseModel handleEntityCRUD(EntityCrudException ex) {
        HttpStatus status = HttpStatus.BAD_REQUEST;

        return ResponseModel
                .builder()
                .status(status.value())
                .message(ex.getMessage())
                // .cause(cause)
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = { CreateEntityFailedException.class })
    public ResponseModel handleCreateEntityFailed(CreateEntityFailedException ex) {
        HttpStatus status = HttpStatus.BAD_REQUEST;

        return ResponseModel
                .builder()
                .status(status.value())
                .message(ex.getMessage())
                // .cause(cause)
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = { MethodArgumentNotValidException.class })
    public ResponseModel handleCreateEntityFailed(MethodArgumentNotValidException ex) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        Map<String, Object> cause = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(
                error -> cause.put(error.getField(), error.getDefaultMessage()));

        return ResponseModel
                .builder()
                .status(status.value())
                .message(ex.getMessage())
                .cause(cause)
                .build();

    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = { IllegalArgumentException.class })
    public ResponseModel handleCreateEntityFailed(IllegalArgumentException ex) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        // Map<String, Object> cause = new HashMap<>();

        // ex.getBindingResult().getFieldErrors().forEach(
        // error -> cause.put(error.getField(), error.getDefaultMessage()));

        return ResponseModel
                .builder()
                .status(status.value())
                .message(ex.getMessage())
                // .cause(cause)
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = { StatusNotFoundException.class })
    public ResponseModel handleStatusNotFound(StatusNotFoundException ex) {
        HttpStatus status = HttpStatus.BAD_REQUEST;

        return ResponseModel
                .builder()
                .status(status.value())
                .message(ex.getMessage())
                // .cause(cause)
                .build();
    }
}
