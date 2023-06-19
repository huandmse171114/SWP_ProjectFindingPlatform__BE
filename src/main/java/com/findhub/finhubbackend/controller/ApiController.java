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
import com.findhub.finhubbackend.entity.entity.Status;
import com.findhub.finhubbackend.service.service.Service;
import com.findhub.finhubbackend.util.Config.ApiPath;
import com.findhub.finhubbackend.util.Config.Var;

@SuppressWarnings("null")
public class ApiController<E, T extends Service<E, S>, S> {

    // private E e;
    // private String eName = e.getClass().getSimpleName();
    // private String ePath = "com.findhub.finhubbackend.";
    // private Class eService = Class.forName(ePath + "service." + eName + "." +
    // eName + "Service");
    // private Class eStatus = Class.forName(e.getClass().getName() + "Status");
    // private Class eCreateModel = Class.forName(ePath + "model" + eName + "." +
    // eName + "CreateModel");
    // private Class eResponseModel = Class.forName(ePath + "model" + eName + "." +
    // eName + "ResponseModel");

    @Autowired
    protected T service;

    public boolean doGetEntities() {
        return false;
    }

    @GetMapping(ApiPath.ALL)
    public List<E> getEntities() {
        return service.getAll();
    }

    public boolean doGet() {
        return false;
    }

    @GetMapping(ApiPath.ID)
    public E get(@PathVariable(Var.ID) int id) {
        return service.findById(id);
    }

    public boolean doAdd() {
        return false;
    }

    // @PostMapping("/")
    // @SuppressWarnings("unchecked")
    public ResponseEntity<?> add(@RequestBody E entity) {

        // if (entity instanceof E) {
        // }

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
    // @SuppressWarnings("unchecked")
    public ResponseEntity<?> update(@PathVariable(Var.ID) int id, @RequestBody E entity) {
        // if(entity instanceof E){}
        // E x = (E) entity;

        if (service.findById(id) == null)
            return new ResponseEntity<>(
                    "Failed to update "
                            + entity.getClass().getSimpleName() + ": "
                            + entity.getClass().getSimpleName() + "[id=" + id + "] not found",
                    HttpStatus.NOT_FOUND);

        if (entity == null)
            return new ResponseEntity<>(
                    "Failed to update "
                            + entity.getClass().getSimpleName()
                            + ": update content is NULL",
                    HttpStatus.BAD_REQUEST);

        E updateE = service.update(id, entity);
        return (updateE != null)
                ? new ResponseEntity<>(
                        "Updated " + entity.getClass().getSimpleName() + "[id=" + id + "] successfully",
                        HttpStatus.OK)
                : new ResponseEntity<>(
                        "Failed to update " + entity.getClass().getSimpleName() + "[id=" + id + "]",
                        HttpStatus.BAD_REQUEST);
    }

    @PutMapping(ApiPath.CHANGE_STATUS)
    public ResponseEntity<?> updateStatus(@PathVariable(Var.ID) int id, @RequestBody int status) {

        E entity = service.findById(id);

        if (entity == null)
            return new ResponseEntity<>(
                    "Failed to update"
                            + entity.getClass().getSimpleName()
                            + "[status=" + ((MyEntity) entity).getStatus() + "] to "
                            + entity.getClass().getSimpleName() + "[status=" + status + "]: "
                            + entity.getClass().getSimpleName() + "[id=" + id + "] not found",
                    HttpStatus.NOT_FOUND);

        return (service.updateStatus(id, status))
                ? new ResponseEntity<>(
                        "Updated"
                                + entity.getClass().getSimpleName()
                                + "[status=" + ((MyEntity) entity).getStatus() + "] to "
                                + entity.getClass().getSimpleName() + "[status=" + status + "] successfully",
                        HttpStatus.OK)
                : new ResponseEntity<>(
                        "Failed to update " + entity.getClass().getSimpleName() + "[id=" + id + "]",
                        HttpStatus.BAD_REQUEST);
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

        return (service.delete(entity))
                ? new ResponseEntity<>(
                        "Deleted " + entity.getClass().getSimpleName() + "[id=" + id + "] successfully",
                        HttpStatus.OK)
                : new ResponseEntity<>(
                        "Failed to delete " + entity.getClass().getSimpleName() + "[id=" + id + "]",
                        HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<?> enable(@RequestBody int id) {
        E entity = service.findById(id);
        return (service.updateStatus(id, Status.ACTIVE))
                ? new ResponseEntity<>(
                        "Enable " + entity.getClass().getSimpleName() + "[id=" + id + "] successfully",
                        HttpStatus.OK)
                : new ResponseEntity<>(
                        "Failed to enable " + entity.getClass().getSimpleName() + "[id=" + id + "]",
                        HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<?> disable(@RequestBody int id) {
        E entity = service.findById(id);
        return (service.updateStatus(id, Status.INACTIVE))
                ? new ResponseEntity<>(
                        "Disable" + entity.getClass().getSimpleName() + "[id=" + id + "] successfully",
                        HttpStatus.OK)
                : new ResponseEntity<>(
                        "Failed to disable" + entity.getClass().getSimpleName() + "[id=" + id + "]",
                        HttpStatus.BAD_REQUEST);
    }
}
