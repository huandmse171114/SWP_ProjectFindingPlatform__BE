package com.findhub.finhubbackend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.findhub.finhubbackend.entity.projectCategoryDetail.ProjectCategoryDetail;

public interface ProjectCategoryDetailRepository extends JpaRepository<ProjectCategoryDetail, Integer> {

    Optional<ProjectCategoryDetail> findById(int id);

    List<ProjectCategoryDetail> findAllById(int id);

    List<ProjectCategoryDetail> findAllByIdContaining(int id);
}
