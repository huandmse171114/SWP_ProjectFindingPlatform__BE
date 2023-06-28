package com.findhub.finhubbackend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.findhub.finhubbackend.entity.projectSkillRequire.ProjectSkillRequire;

public interface ProjectSkillRequireRepository extends JpaRepository<ProjectSkillRequire, Integer> {

    List<ProjectSkillRequire> findAllByProjectId(int id);

    Optional<ProjectSkillRequire> findById(int id);

    List<ProjectSkillRequire> findAllById(int id);

    List<ProjectSkillRequire> findAllByIdContaining(int id);
}
