package com.findhub.finhubbackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.findhub.finhubbackend.entity.entity.MyEntity;
import com.findhub.finhubbackend.service.service.Service;
import com.findhub.finhubbackend.util.Config.ApiPath;
import com.findhub.finhubbackend.util.Config.Var;

@SuppressWarnings("null")
public class ApiController<E, T extends Service<E, S>, S> {

    // private E x;
    // private String classSimpleName = x.getClass().getSimpleName();
    // private String className = x.getClass().getName();
    // private Class eService = Class.forName(className + "Service");
    // private Class eStatus = Class.forName(className + "Status");

    @Autowired
    protected T service;

    @GetMapping(ApiPath.ALL)
    public List<E> getEntities() {
        return service.getAll();
    }

    @GetMapping(ApiPath.ID)
    public E get(@PathVariable(Var.ID) int id) {
        return service.findById(id);
    }

    // @PostMapping(ApiPath.ADD)
    public ResponseEntity<String> add(@RequestBody E entity) {

        if (entity == null)
            return new ResponseEntity<>(
                    "Failed to add " + entity.getClass().getSimpleName() + ": add content is NULL",
                    HttpStatus.BAD_REQUEST);

        service.save(entity);
        return new ResponseEntity<>(
                "Added new " + entity.getClass().getSimpleName() + " successfully",
                HttpStatus.OK);
    }

    @PutMapping(ApiPath.ID)
    public ResponseEntity<String> update(@PathVariable(Var.ID) int id, @RequestBody E entity) {

        if (service.findById(id) == null)
            return new ResponseEntity<>(
                    "Failed to update "
                            + entity.getClass().getSimpleName() + ": "
                            + entity.getClass().getSimpleName() + "[id=" + id
                            + "] not found",
                    HttpStatus.NOT_FOUND);

        if (entity == null)
            return new ResponseEntity<>(
                    "Failed to update "
                            + entity.getClass().getSimpleName()
                            + ": update content is NULL",
                    HttpStatus.BAD_REQUEST);

        service.update(id, entity);
        return new ResponseEntity<>(
                "Updated " + entity.getClass().getSimpleName() + "[id=" + id + "] successfully",
                HttpStatus.OK);
    }

    @PutMapping(ApiPath.CHANGE_STATUS)
    public ResponseEntity<String> updateStatus(@PathVariable(Var.ID) int id, @RequestBody int status) {

        E entity = service.findById(id);

        if (entity == null)
            return new ResponseEntity<>(
                    "Failed to update"
                            + entity.getClass().getSimpleName()
                            + "[status=" + ((MyEntity) entity).getStatus() + "] to "
                            + entity.getClass().getSimpleName() + "[status=" + status + "]: "
                            + entity.getClass().getSimpleName() + "[id=" + id + "] not found",
                    HttpStatus.NOT_FOUND);

        service.updateStatus(id, status);
        return new ResponseEntity<>(
                "Updated"
                        + entity.getClass().getSimpleName()
                        + "[status=" + ((MyEntity) entity).getStatus() + "] to "
                        + entity.getClass().getSimpleName() + "[status=" + status + "] successfully",
                HttpStatus.OK);
    }

    @DeleteMapping("/")
    public ResponseEntity<String> delete(@RequestBody int id) {

        E entity = service.findById(id);

        if (entity == null)
            return new ResponseEntity<>(
                    "Failed to delete "
                            + entity.getClass().getSimpleName() + ": "
                            + entity.getClass().getSimpleName() + "[id=" + id + "] not found",
                    HttpStatus.NOT_FOUND);

        service.delete(entity);
        return new ResponseEntity<>(
                "Deleted " + entity.getClass().getSimpleName() + "[id=" + id + "] successfully",
                HttpStatus.OK);
    }

}
