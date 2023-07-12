package com.findhub.finhubbackend.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.findhub.finhubbackend.entity.projectCategory.ProjectCategory;

public interface ProjectCategoryRepository extends Repo<ProjectCategory> {

    Optional<ProjectCategory> findById(int id);

    List<ProjectCategory> findAllById(int id);

    List<ProjectCategory> findAllByIdContaining(int id);

    @Transactional
    @Modifying
    @Query(value = """
            UPDATE Project_Category
            SET
                CategoryId = :cateId ,
                Status = :status
            WHERE Id = :id
            """, nativeQuery = true)
    void updateById(
        @Param("id") int id,
        @Param("status") int status,
        @Param("cateId") int CategoryId
    );
}
