package com.findhub.finhubbackend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface Repo<T> extends JpaRepository<T, Integer> {
    /**
     * tìm chính xác T
     */
    Optional<T> findById(int id);

    /**
     * tìm tất cả T có id gần đúng
     */
    List<T> findAllByIdLike(int id);

    /**
     * tìm tất cả T có chính xác
     */
    List<T> findAllByNameLike(String name);

    /**
     * tìm tất cả T với status
     */
    List<T> findAllByStatus(int status);
}
