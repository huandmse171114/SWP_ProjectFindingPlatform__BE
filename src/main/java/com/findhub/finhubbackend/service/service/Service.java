package com.findhub.finhubbackend.service.service;

import java.util.List;

import com.findhub.finhubbackend.entity.entity.Status;

public interface Service<E, S> {
    /**
     * add new T to DB
     */
    public E add(E entity);

    /**
     * update T
     */
    public E update(int id, E entity);

    /**
     * update T
     */
    public E update(E oldT, E newT);

    /**
     * set new status of T
     */
    public boolean updateStatus(int id, int status);

    /**
     * set new status of T
     */
    public boolean updateStatus(int id, S status);

    /**
     * set new status of T
     */
    public boolean updateStatus(int id, Status status);

    /**
     * set new status of T
     */
    public boolean updateStatus(E entity, int status);

    /**
     * set new status of T
     */
    public boolean updateStatus(E entity, S status);

    /**
     * set new status of T
     */
    public boolean updateStatus(E entity, Status status);

    /**
     * delete existed T from DB
     */
    public boolean delete(int id);

    /**
     * delete existed T from DB
     */
    public boolean delete(E entity);

    /**
     * find T by id (exact id)
     */
    public E get(int id);

    public List<E> findAllById(int id);

    /**
     * get all Ts in DB
     */
    public List<E> getAll();

    /**
     * tìm tất cả T có id gần đúng
     */
    public List<E> findAllByIdContaining(int id);

    /**
     * tìm tất cả T với status
     */
    public List<E> findAllByStatus(int status);

    /**
     * tìm tất cả T với status
     */
    public List<E> findAllByStatus(S status);

    /**
     * save T
     */
    public E save(E entity);
}
