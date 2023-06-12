package com.findhub.finhubbackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
    public E add(@RequestBody E entity) {
        return service.add(entity);
    }

    @PutMapping(ApiPath.UPDATE)
    public E update(@PathVariable(Var.ID) int id, @RequestBody E entity) {
        return service.update(id, entity);
    }

    @DeleteMapping(ApiPath.DELETE)
    public boolean delete(@RequestBody int id) {
        E entity = service.findById(id);
        return service.delete(entity);
    }

    @PostMapping(ApiPath.CHANGE_STATUS)
    public boolean updateStatus(@RequestBody int id, @RequestBody int status) {
        return service.updateStatus(id, status);
    }
}
