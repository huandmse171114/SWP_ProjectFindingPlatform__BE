package com.findhub.finhubbackend.service.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.findhub.finhubbackend.entity.entity.MyEntity;
import com.findhub.finhubbackend.repository.Repo;

/**
 * T1: entity
 * <p>
 * T2: repo
 * <p>
 * E: status
 * <p>
 */
@SuppressWarnings("rawtypes")
public class ServiceImpl<E extends MyEntity, R extends Repo<E>, S extends Enum>
        implements Service<E, S> {

    private int getValue(S e) {
        return Integer.parseInt(e.toString());
    }

    @Autowired
    protected R repo;

    @Override
    public E add(E entity) {
        return (entity != null) ? repo.save(entity) : null;
    }

    @Override
    public boolean updateStatus(int id, int status) {
        Optional<E> entity = repo.findById(id);
        return updateStatus(entity.isPresent() ? entity.get() : null, status);
    }

    @Override
    public boolean updateStatus(int id, S status) {
        return updateStatus(id, getValue(status));
    }

    @Override
    public boolean updateStatus(E entity, int status) {
        if (entity != null) {
            entity.setStatus(status);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateStatus(E entity, S status) {
        return updateStatus(entity, getValue(status));
    }

    @Override
    public boolean delete(int id) {
        Optional<E> entity = repo.findById(id);
        return delete(entity.isPresent() ? entity.get() : null);
    }

    @Override
    public boolean delete(E entity) {
        if (entity != null) {
            repo.delete(entity);
            return true;
        }

        return false;
    }

    @Override
    public E findById(int id) {
        Optional<E> entity = repo.findById(id);
        return entity.isPresent() ? entity.get() : null;
    }

    @Override
    public List<E> findAllByIdStartingWith(int id) {
        return repo.findAllByIdContaining(id);

    }

    @Override
    public List<E> findAllByStatus(int status) {
        return repo.findAllByStatus(status);
    }

    @Override
    public List<E> findAllByStatus(S status) {
        return repo.findAllByStatus(getValue(status));
    }

    @Override
    public List<E> getAll() {
        return repo.findAll();
    }

    @Override
    public void save(E entity) {
        repo.save(entity);
    }

    @Override
    public E update(int id, E entity) {
        Optional<E> old = repo.findById(id);
        return update(old.isPresent() ? old.get() : null, entity);
    }

    @Override
    public E update(E oldT, E newT) {
        if (newT != null && oldT != null) {
            int id = oldT.getId();
            newT.setId(id);

            return repo.save(newT);
        }

        return null;
    }

}
