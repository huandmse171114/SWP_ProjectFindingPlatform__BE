package com.findhub.finhubbackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.findhub.finhubbackend.service.service.Service;
import com.findhub.finhubbackend.util.Config.ApiPath;
import com.findhub.finhubbackend.util.Config.Var;

public class ApiController<E, T extends Service<E, S>, S> {

    private E x;
    private String classSimpleName = x.getClass().getSimpleName();
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

    @PostMapping(ApiPath.ADD)
    public ResponseEntity<String> add(@RequestBody E entity) {
        if (entity == null)
            return new ResponseEntity<>(
                    "Failed to add " + classSimpleName, HttpStatus.BAD_REQUEST);

        service.save(entity);
        return new ResponseEntity<>(
                "Added new " + classSimpleName + " successfully",
                HttpStatus.OK);
    }

    @PutMapping(ApiPath.UPDATE)
    public ResponseEntity<String> update(@PathVariable(Var.ID) int id, @RequestBody E entity) {
        if (service.findById(id) == null)
            return new ResponseEntity<>(
                    "Failed to update " + classSimpleName + ": " + classSimpleName + "[" + id + "] not found",
                    HttpStatus.NOT_FOUND);

        if (entity == null)
            return new ResponseEntity<>(
                    "Failed to update " + classSimpleName + ": update content is NULL",
                    HttpStatus.BAD_REQUEST);

        service.update(id, entity);
        return new ResponseEntity<>(
                "Updated " + classSimpleName + "[" + id + "] successfully",
                HttpStatus.OK);
    }

    @DeleteMapping(ApiPath.DELETE)
    public ResponseEntity<String> delete(@RequestBody int id) {
        E entity = service.findById(id);

        if (entity == null)
            return new ResponseEntity<>(
                    "Failed to delete " + classSimpleName + ": " + classSimpleName + "[" + id + "] not found",
                    HttpStatus.NOT_FOUND);

        service.delete(entity);
        return new ResponseEntity<>(
                "Deleted " + classSimpleName + "[" + id + "] successfully",
                HttpStatus.OK);
    }

    @PostMapping(ApiPath.CHANGE_STATUS)
    public ResponseEntity<String> updateStatus(@RequestBody int id, @RequestBody int status) {
        E entity = service.findById(id);

        if (entity == null)
            return new ResponseEntity<>(
                    "Failed to update Status[" + status + "] of" + classSimpleName
                            + ": " + classSimpleName + "[" + id + "] not found",
                    HttpStatus.NOT_FOUND);

        service.updateStatus(id, status);
        return new ResponseEntity<>(
                "Updated Status[" + status + "] of" + classSimpleName + "[" + id + "] successfully",
                HttpStatus.OK);
    }
}
