package com.findhub.finhubbackend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.findhub.finhubbackend.dto.CategoryDTO;
import com.findhub.finhubbackend.entity.category.Category;

// @Repository
public interface CategoryRepository extends Repo<Category> {
    /**
     * tìm chính xác name
     */
    Optional<Category> findByName(String name);

    /**
     * tìm tất cả Category có chính xác
     */
    List<Category> findAllByNameContaining(String name);

    @Query(value = """
            SELECT
                c.Name
            FROM
                Project_Category pc
                INNER JOIN Category c ON pc.CategoryId = c.Id
            WHERE
                pc.ProjectId = :id
			""", nativeQuery = true)
    List<CategoryDTO> getNameByProjectId(@Param("id") int id);

    boolean existsByName(String name);

}
