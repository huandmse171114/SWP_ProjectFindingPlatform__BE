package com.findhub.finhubbackend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.findhub.finhubbackend.entity.projectSkill.ProjectSkill;

public interface ProjectSkillRepository extends JpaRepository<ProjectSkill, Integer> {

    List<ProjectSkill> findAllByProjectId(int id);

    Optional<ProjectSkill> findById(int id);

    List<ProjectSkill> findAllById(int id);

    List<ProjectSkill> findAllByIdContaining(int id);
}
