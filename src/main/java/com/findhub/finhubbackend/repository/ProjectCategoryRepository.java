package com.findhub.finhubbackend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.findhub.finhubbackend.entity.projectCategory.ProjectCategory;

public interface ProjectCategoryRepository extends JpaRepository<ProjectCategory, Integer> {

    Optional<ProjectCategory> findById(int id);

    List<ProjectCategory> findAllById(int id);

    List<ProjectCategory> findAllByIdContaining(int id);
}
