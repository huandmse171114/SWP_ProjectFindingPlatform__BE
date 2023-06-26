package com.findhub.finhubbackend.controller;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.findhub.finhubbackend.entity.entity.MyEntity;
import com.findhub.finhubbackend.entity.entity.Status;
import com.findhub.finhubbackend.model.ErrorResponseModel;
import com.findhub.finhubbackend.service.service.Service;
import com.findhub.finhubbackend.util.Config.SubPath;
import com.findhub.finhubbackend.util.Config.Var;

@SuppressWarnings("null")
public class ApiController<E, T extends Service<E, S>, S> {

    @Autowired
    protected T service;

    @SuppressWarnings("unchecked")
    private Class<E> getEntityClass() {
        Type type = getClass().getGenericSuperclass();
        ParameterizedType paramType = (ParameterizedType) type;
        return (Class<E>) paramType.getActualTypeArguments()[0];
    }

    public ResponseEntity<?> errorResponse(String errorMessage, HttpStatus status) {
        return ResponseEntity
                .status(status)
                .body(ErrorResponseModel
                        .builder()
                        .status(status)
                        .message(errorMessage)
                        .build());
    }

    public ResponseEntity<?> errorResponse(HttpStatus status) {
        return errorResponse("Failed", status);
    }

    private E getInstance() {
        try {
            return getEntityClass().getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private E instance = getInstance();
    protected String entityName = instance.getClass().getSimpleName();

    @GetMapping(SubPath.ALL)
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @GetMapping(SubPath.ID)
    public ResponseEntity<?> get(@PathVariable(Var.ID) int id) {
        E entity = service.get(id);

        return (entity == null)
                ? ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(ErrorResponseModel
                                .builder()
                                .status(HttpStatus.NOT_FOUND)
                                .message("Cannot find " + entityName + "[id=" + id + "]")
                                .build())
                : ResponseEntity
                        .status(HttpStatus.OK)
                        .body(entity);
    }

    // @PostMapping("/")
    @SuppressWarnings("unchecked")
    public ResponseEntity<?> create(@RequestBody Object entity) {
        if (entity == null)
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(ErrorResponseModel
                            .builder()
                            .status(HttpStatus.NOT_FOUND)
                            .message("Failed to add " + entityName + ": add content is NULL")
                            .build());

        if (entity.getClass().isInstance(instance)) {
            E created = service.save((E) entity);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(((MyEntity) created).getId())
                    .toUri();

            return ResponseEntity.created(location).body(created);

        } else
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(ErrorResponseModel
                            .builder()
                            .status(HttpStatus.BAD_REQUEST)
                            .message("Request Object is not " + entityName)
                            .build());

    }

    @PutMapping(SubPath.ID)
    public ResponseEntity<?> update(@PathVariable(Var.ID) int id, @RequestBody E entity) {

        if (service.get(id) == null)
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(ErrorResponseModel
                            .builder()
                            .status(HttpStatus.NOT_FOUND)
                            .message("Failed to update " + entityName + ": " + entityName + "[id=" + id + "] not found")
                            .build());

        if (entity == null)
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(ErrorResponseModel
                            .builder()
                            .status(HttpStatus.BAD_REQUEST)
                            .message("Failed to update " + entityName + ": update content is NULL")
                            .build());

        if (entity.getClass().isInstance(instance)) {
            E updateE = service.update(id, entity);
            return (updateE != null)
                    ? new ResponseEntity<>(
                            "Updated " + entityName + "[id=" + id + "] successfully",
                            HttpStatus.OK)
                    : new ResponseEntity<>(
                            "Failed to update " + entityName + "[id=" + id + "]",
                            HttpStatus.BAD_REQUEST);
        } else
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(ErrorResponseModel
                            .builder()
                            .status(HttpStatus.BAD_REQUEST)
                            .message("Request Object is not " + entityName)
                            .build());
    }

    @PutMapping(SubPath.CHANGE_STATUS)
    public ResponseEntity<?> updateStatus(@PathVariable(Var.ID) int id, @RequestBody int status) {

        E entity = service.get(id);

        if (entity == null)
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(ErrorResponseModel
                            .builder()
                            .status(HttpStatus.NOT_FOUND)
                            .message("Failed to update"
                                    + entityName + "[status=" + ((MyEntity) entity).getStatus() + "] to "
                                    + entityName + "[status=" + status + "]: "
                                    + entityName + "[id=" + id + "] not found")
                            .build());

        return (service.updateStatus(id, status))
                ? ResponseEntity
                        .status(HttpStatus.OK)
                        .body(ErrorResponseModel
                                .builder()
                                .status(HttpStatus.OK)
                                .message("Updated"
                                        + entityName + "[status=" + ((MyEntity) entity).getStatus() + "] to "
                                        + entityName + "[status=" + status + "] successfully")
                                .build())
                : ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body(ErrorResponseModel
                                .builder()
                                .status(HttpStatus.BAD_REQUEST)
                                .message("Failed to update " + entityName + "[id=" + id + "]")
                                .build());
    }

    @DeleteMapping(SubPath.ID)
    public ResponseEntity<?> delete(@PathVariable(Var.ID) int id) {

        E entity = service.get(id);

        if (entity == null)
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(ErrorResponseModel
                            .builder()
                            .status(HttpStatus.NOT_FOUND)
                            .message("Failed to delete "
                                    + entityName + ": "
                                    + entityName + "[id=" + id + "] not found")
                            .build());

        return (service.delete(entity))
                ? ResponseEntity
                        .status(HttpStatus.OK)
                        .body(ErrorResponseModel
                                .builder()
                                .status(HttpStatus.OK)
                                .message("Deleted " + entityName + "[id=" + id + "] successfully")
                                .build())
                : ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body(ErrorResponseModel
                                .builder()
                                .status(HttpStatus.BAD_REQUEST)
                                .message("Failed to delete " + entityName + "[id=" + id + "]")
                                .build());
    }

    public ResponseEntity<?> enable(@RequestBody int id) {
        return updateStatus(id, Status.ACTIVE.getValue());
    }

    public ResponseEntity<?> disable(@RequestBody int id) {
        return updateStatus(id, Status.INACTIVE.getValue());
    }
}
