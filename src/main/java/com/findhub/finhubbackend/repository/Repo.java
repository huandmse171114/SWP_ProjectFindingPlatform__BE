package com.findhub.finhubbackend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface Repo<E> extends JpaRepository<E, Integer> {
    /**
     * tìm chính xác E
     */
    Optional<E> findById(int id);

    /**
     * tìm tất cả E có id gần đúng
     */
    List<E> findAllByIdContaining(int id);

    /**
     * tìm tất cả E với status
     */
    List<E> findAllByStatus(int status);
}
