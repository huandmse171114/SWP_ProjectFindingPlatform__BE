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
import com.findhub.finhubbackend.exception.EntityFoundException;
import com.findhub.finhubbackend.exception.EntityNotFoundException;
import com.findhub.finhubbackend.exception.StatusNotFoundException;
import com.findhub.finhubbackend.model.model.ApiResponse;

@RestControllerAdvice
public class AppExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = { EntityFoundException.class })
    public ApiResponse handleEntityExisted(EntityFoundException ex) {
        HttpStatus status = HttpStatus.BAD_REQUEST;

        return ApiResponse
                .builder()
                .status(status.value())
                .message(ex.getMessage())
                // .cause(ex)
                .build();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = { EntityNotFoundException.class })
    public ApiResponse handleEntityNotFound(EntityNotFoundException ex) {
        HttpStatus status = HttpStatus.NOT_FOUND;

        return ApiResponse
                .builder()
                .status(status.value())
                .message(ex.getMessage())
                // .cause(ex)
                .build();

        // return response(ex, status);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = { EntityCrudException.class })
    public ApiResponse handleEntityCRUD(EntityCrudException ex) {
        HttpStatus status = HttpStatus.BAD_REQUEST;

        return ApiResponse
                .builder()
                .status(status.value())
                .message(ex.getMessage())
                // .cause(cause)
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = { CreateEntityFailedException.class })
    public ApiResponse handleCreateEntityFailed(CreateEntityFailedException ex) {
        HttpStatus status = HttpStatus.BAD_REQUEST;

        return ApiResponse
                .builder()
                .status(status.value())
                .message(ex.getMessage())
                // .cause(cause)
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = { MethodArgumentNotValidException.class })
    public ApiResponse handleCreateEntityFailed(MethodArgumentNotValidException ex) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        Map<String, Object> cause = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(
                error -> cause.put(error.getField(), error.getDefaultMessage()));

        return ApiResponse
                .builder()
                .status(status.value())
                .message(ex.getMessage())
                .cause(cause)
                .build();

    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = { IllegalArgumentException.class })
    public ApiResponse handleCreateEntityFailed(IllegalArgumentException ex) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        // Map<String, Object> cause = new HashMap<>();

        // ex.getBindingResult().getFieldErrors().forEach(
        // error -> cause.put(error.getField(), error.getDefaultMessage()));

        return ApiResponse
                .builder()
                .status(status.value())
                .message(ex.getMessage())
                // .cause(cause)
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = { StatusNotFoundException.class })
    public ApiResponse handleStatusNotFound(StatusNotFoundException ex) {
        HttpStatus status = HttpStatus.BAD_REQUEST;

        return ApiResponse
                .builder()
                .status(status.value())
                .message(ex.getMessage())
                // .cause(cause)
                .build();
    }
}
