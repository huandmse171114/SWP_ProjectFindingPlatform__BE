package com.findhub.finhubbackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.findhub.finhubbackend.service.service.Service;
import com.findhub.finhubbackend.util.Config.ApiPath;
import com.findhub.finhubbackend.util.Config.Var;

public class MyController<E, T extends Service<E, S>, S> {

    @Autowired
    protected T service;

    @GetMapping(ApiPath.ALL)
    public List<E> getEntities() {
        return service.getAll();
    }

    @GetMapping(ApiPath.ID)
    public E getEntitiesById(@PathVariable(Var.ID) int id) {
        return service.findById(id);
    }

    @PostMapping(ApiPath.ADD)
    public E addEntities(@RequestBody E entity) {
        return service.add(entity);
    }

    @PostMapping(ApiPath.UPDATE)
    public E updateEntitiesById(@PathVariable(Var.ID) int id, @RequestBody E entity) {
        return service.update(id, entity);
    }

    @PostMapping(ApiPath.DELETE)
    public boolean deleteEntitiesById(@RequestBody int id) {
        E entity = service.findById(id);
        return service.delete(entity);
    }

    @PostMapping(ApiPath.CHANGE_STATUS)
    public boolean changeStatusEntity(@RequestBody int id, @RequestBody int status) {
        return service.changeStatus(id, status);
    }
}
