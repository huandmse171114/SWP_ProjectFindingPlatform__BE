package com.findhub.finhubbackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.findhub.finhubbackend.service.service.Service;
import com.findhub.finhubbackend.util.Config.*;

public class MyController<E, T extends Service<E, S>, S> {

    @Autowired
    protected T service;

    @GetMapping(Path.ALL)
    public List<E> getEntities() {
        return service.getAll();
    }

    @GetMapping(Path.ID)
    public E getEntitiesById(@PathVariable(Var.ID) int id) {
        return service.findById(id);
    }

    @PostMapping(Path.ADD)
    public E addEntities(@RequestBody E entity) {
        return service.add(entity);
    }

    @PostMapping(Path.UPDATE)
    public E updateEntitiesById(@PathVariable(Var.ID) int id, @RequestBody E entity) {
        return service.update(id, entity);
    }

    @PostMapping(Path.DELETE)
    public boolean deleteEntitiesById(@RequestBody int id) {
        E entity = service.findById(id);
        return service.delete(entity);
    }
}
