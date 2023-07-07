package com.findhub.finhubbackend.controller;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.URI;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

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
import com.findhub.finhubbackend.entity.project.ProjectStatus;
import com.findhub.finhubbackend.exception.CreateEntityFailedException;
import com.findhub.finhubbackend.exception.EntityCrudException;
import com.findhub.finhubbackend.exception.EntityNotFoundException;
import com.findhub.finhubbackend.model.create.UpdateStatusModel;
import com.findhub.finhubbackend.model.model.ApiResponse;
import com.findhub.finhubbackend.model.model.StatusModel;
import com.findhub.finhubbackend.service.service.Service;
import com.findhub.finhubbackend.util.Config.SubPath;
import com.findhub.finhubbackend.util.Config.Var;
import com.findhub.finhubbackend.util.Utils;

@SuppressWarnings("null")
public class ApiController<E, T extends Service<E, S>, S extends Enum<S>> {

    @Autowired
    protected T service;

    public ResponseEntity<?> response(String errorMessage, HttpStatus status) {
        return ResponseEntity
                .status(status)
                .body(ApiResponse
                    .builder()
                        // .status(status)
                        .message(errorMessage)
                    .build()
                );
    }

    public ResponseEntity<?> response(HttpStatus status) {
        return response("Failed", status);
    }

    @SuppressWarnings("unchecked")
    private Class<E> getEntityClass() {
        Type type = getClass().getGenericSuperclass();
        ParameterizedType paramType = (ParameterizedType) type;
        return (Class<E>) paramType.getActualTypeArguments()[0];
    }

    @SuppressWarnings("unchecked")
    private Class<S> getStatusClass() {
        Type type = getClass().getGenericSuperclass();
        ParameterizedType paramType = (ParameterizedType) type;
        return (Class<S>) paramType.getActualTypeArguments()[2];
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

        if (entity == null)
            throw new EntityNotFoundException(entityName, id);

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(entity);
    }

    // @PostMapping("/")
    @SuppressWarnings("unchecked")
    public ResponseEntity<?> create(@RequestBody Object entity) {
        if (entity == null)
            throw new CreateEntityFailedException("Failed to add " + entityName + ": add content is NULL");

        if (!entity.getClass().isInstance(instance))
            throw new CreateEntityFailedException("Request Object is not " + entityName);

        E created = service.save((E) entity);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
            .path(SubPath.ID)
            .buildAndExpand(((MyEntity) created).getId())
            .toUri();

        return ResponseEntity
            .created(location)
            .body(created);

    }

    // @PutMapping()
    public ResponseEntity<?> update(@RequestBody E entity) {

        if (entity == null)
            throw new EntityCrudException("Failed to update " + entityName + ": update content is NULL");

        int id = ((MyEntity) entity).getId();

        if (service.get(id) == null)
            throw new EntityNotFoundException(entityName, id);

        if (!entity.getClass().isInstance(instance))
            throw new EntityCrudException("Request Object is not " + entityName);

        E updateE = service.update(id, entity);

        if (updateE == null)
            throw new EntityCrudException("Failed to update " + entityName + "[id=" + id + "]");

        return new ResponseEntity<>(updateE, HttpStatus.OK);

    }

    @PutMapping(SubPath.STATUS)
    public ResponseEntity<?> updateStatus(@RequestBody UpdateStatusModel u) {

        int id = u.getId();
        int status = u.getStatus();

        E entity = service.get(id);

        if (entity == null)
            throw new EntityCrudException(
                "Failed to update"
                        + entityName + "[status=" + ((MyEntity) entity).getStatus() + "] to "
                        + entityName + "[status=" + status + "]: "
                        + entityName + "[id=" + id + "] not found"
            );

        return (service.updateStatus(id, status))
                ? ResponseEntity
                    .status(HttpStatus.OK)
                    .body(
                        ApiResponse
                            .builder()
                                // .status(HttpStatus.OK)
                                .message(
                                    "Updated"
                                        + entityName + "[status=" + ((MyEntity) entity).getStatus() + "] to "
                                        + entityName + "[status=" + status + "] successfully"
                                )
                            .build()
                    )
                : ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(
                        ApiResponse
                            .builder()
                                // .status(HttpStatus.BAD_REQUEST)
                                .message("Failed to update " + entityName + "[id=" + id + "]")
                            .build()
                    );
    }

    @DeleteMapping(SubPath.ID)
    public ResponseEntity<?> delete(@PathVariable(Var.ID) int id) {

        E entity = service.get(id);

        if (entity == null)
            throw new EntityNotFoundException(entityName, id);

        return (service.delete(entity))
                ? ResponseEntity
                    .status(HttpStatus.OK)
                    .body(
                        ApiResponse
                            .builder()
                                // .status(HttpStatus.OK)
                                .message("Deleted " + entityName + "[id=" + id + "] successfully")
                            .build()
                    )
                : ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(
                        ApiResponse
                            .builder()
                                // .status(HttpStatus.BAD_REQUEST)
                                .message("Failed to delete " + entityName + "[id=" + id + "]")
                            .build()
                    );
    }

    public ResponseEntity<?> enable(@RequestBody int id) {
        return updateStatus(
            UpdateStatusModel
                .builder()
                    .id(id)
                    .status(Status.ACTIVE.getValue())
                .build()
        );
    }

    public ResponseEntity<?> disable(@RequestBody int id) {
        return updateStatus(
            UpdateStatusModel
                .builder()
                    .id(id)
                    .status(Status.INACTIVE.getValue())
                .build()
        );
    }

    private List<StatusModel> allStatus() {
        List<StatusModel> model = new ArrayList<>();
        for (var s : EnumSet.allOf(getStatusClass())) {
            String name = Utils.capitalize(s.name());
            model.add(
                StatusModel
                    .builder()
                        .id(s.ordinal())
                        .name(name)
                    .build()
            );
        }
        return model;
    }

    protected List<StatusModel> EStatus = allStatus();

    private boolean isExisted(String str) {
        for (var s : EStatus)
            if (s.getName().equals(str.toUpperCase()))
                return true;

        return false;
    }

    @GetMapping(SubPath.STATUS_ALL)
    public ResponseEntity<?> getAllStatus() {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(EStatus);
    }

    @GetMapping(SubPath.STATUS_KEYWORD)
    public ResponseEntity<?> getByStatus(@PathVariable(Var.KEYWORD) String keyword) {

        if (!isExisted(keyword))
            throw new EntityNotFoundException(keyword + " not found in Status");

        List<E> Es;
        if (Utils.isNum(keyword)) {
            int id = Integer.parseInt(keyword);
            Es = service.findAllByStatus(id);
        } else {
            int status = ProjectStatus.valOf(keyword);
            Es = service.findAllByStatus(status);
        }

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(Es);
    }

    @GetMapping(SubPath.ACTIVE)
    public ResponseEntity<?> getActive() {
        List<E> Es = service.findAllByStatus(Status.ACTIVE.getValue());
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(Es);

    }

    @GetMapping(SubPath.INACTIVE)
    public ResponseEntity<?> getInActive() {
        List<E> Es = service.findAllByStatus(Status.INACTIVE.getValue());
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(Es);

    }
}
