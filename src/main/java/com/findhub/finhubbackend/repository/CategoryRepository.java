package com.findhub.finhubbackend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.findhub.finhubbackend.entity.category.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>, Repo<Category> {
    /**
     * tìm chính xác name
     */
    Optional<Category> findByName(String name);

    /**
     * tìm tất cả Category có chính xác
     */
    List<Category> findAllByNameContaining(String name);
}
