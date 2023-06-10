package com.findhub.finhubbackend.service.service;

import java.util.List;

public interface Service<T, E> {
    /**
     * add new T to DB
     */
    public T add(T entity);

    /**
     * update T
     */
    public T update(int id, T entity);

    /**
     * update T
     */
    public T update(T oldT, T newT);

    /**
     * set new status of T
     */
    public boolean changeStatus(int id, int status);

    /**
     * set new status of T
     */
    public boolean changeStatus(int id, E status);

    /**
     * set new status of T
     */
    public boolean changeStatus(T entity, int status);

    /**
     * set new status of T
     */
    public boolean changeStatus(T entity, E status);

    /**
     * delete existed T from DB
     */
    public boolean delete(int id);

    /**
     * delete existed T from DB
     */
    public boolean delete(T entity);

    /**
     * find T by id (exact id)
     */
    public T findById(int id);

    /**
     * get all Ts in DB
     */
    public List<T> getAll();

    /**
     * tìm tất cả T có id gần đúng
     */
    public List<T> findAllByIdLike(int id);

    /**
     * tìm tất cả T với status
     */
    public List<T> findAllByStatus(int status);

    /**
     * tìm tất cả T với status
     */
    public List<T> findAllByStatus(E status);

    /**
     * save T
     */
    public void save(T entity);
}
